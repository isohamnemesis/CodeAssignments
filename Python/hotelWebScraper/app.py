from flask import Flask, request, jsonify
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.options import Options
from seleniumwire import webdriver
import requests
from lxml import etree
import time
import os

app = Flask(__name__)

def init_webdriver(proxy_server, proxy_username, proxy_password, timezone, language):
    chrome_options = Options()
    chrome_options.add_argument("--headless")
    chrome_options.add_argument("--disable-gpu")
    chrome_options.add_argument("--window-size=1920,1080")
    chrome_options.add_argument("--no-sandbox")  
    chrome_options.add_argument("--disable-dev-shm-usage")  
    chrome_options.add_argument(f"--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
    chrome_options.add_argument("--disable-webrtc")
    chrome_options.add_argument(timezone)
    chrome_options.add_argument(language)
    chrome_options.add_argument("--incognito")

    # Configure Selenium Wire options
    options = {
        'proxy': {
            'http': f'http://{proxy_username}:{proxy_password}@{proxy_server}',
            'https': f'https://{proxy_username}:{proxy_password}@{proxy_server}',
        }
    }

    # Initialize ChromeDriver with the correct binary location
    chrome_options.binary_location = os.getenv('GOOGLE_CHROME_SHIM', '/usr/bin/google-chrome')
    driver = webdriver.Chrome(
        executable_path='/usr/bin/chromedriver',
        seleniumwire_options=options,
        options=chrome_options
    )
    return driver

@app.route('/getPrices', methods=['GET'])
def get_prices_endpoint():
    # Get query parameters
    checkin_date = request.args.get('checkin_date')
    checkout_date = request.args.get('checkout_date')
    url = request.args.get('url')
    country = request.args.get('country')
    

    if not all([checkin_date, checkout_date, url, country]):
        return jsonify({"error": "Missing required parameters"}), 400
    
    servers = {
        'USA': {
            'proxy_server':"173.211.0.148:6641", 
            'proxy_username':"dhdjpnlp", 
            'proxy_password':"vqf4s5vdhux8", 
            'timezone':"--timezone=America/Los_Angeles",
            'language':"--lang=en-US"
        },
        'United Kingdom': {
            'proxy_server':"94.177.5.219:6552", 
            'proxy_username':"dhdjpnlp", 
            'proxy_password':"vqf4s5vdhux8", 
            'timezone':"--timezone=Europe/London",
            'language':"--lang=en-GB"
        },
        'France': {
            'proxy_server':"216.173.74.97:5777", 
            'proxy_username':"dhdjpnlp", 
            'proxy_password':"vqf4s5vdhux8", 
            'timezone':"--timezone=Europe/Paris",
            'language':"--lang=fr-FR"
        },
        'Germany': {
            'proxy_server':"45.150.176.212:6085", 
            'proxy_username':"dhdjpnlp", 
            'proxy_password':"vqf4s5vdhux8", 
            'timezone':"--timezone=Europe/Berlin",
            'language':"--lang=de-DE"
        },
        'Canada': {
            'proxy_server':"31.57.41.176:5752", 
            'proxy_username':"dhdjpnlp", 
            'proxy_password':"vqf4s5vdhux8", 
            'timezone':"--timezone=America/Toronto",
            'language':"--lang=en-CA"
        }
    }
    
    proxy = servers[country]
    proxy_username = proxy['proxy_username']
    proxy_password = proxy['proxy_password']
    proxy_server = proxy['proxy_server']
    language = proxy['language']
    timezone = proxy['timezone']

    try:
        prices = getPrices(
            checkin_date=checkin_date,
            checkout_date=checkout_date,
            url=url,
            proxy_server=proxy_server,
            proxy_username=proxy_username,
            proxy_password=proxy_password,
            timezone=timezone,
            language=language
        )
        return jsonify({"prices": prices})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

def getPrices(checkin_date, checkout_date, url, proxy_server, proxy_username, proxy_password, timezone, language):
    driver = init_webdriver(proxy_server, proxy_username, proxy_password, timezone, language)
    resultant_url = ""
    
    def robust_get(url, retries=3):
        for attempt in range(retries):
            try:
                driver.get(url)
                WebDriverWait(driver, 20).until(
                    EC.presence_of_element_located((By.TAG_NAME, "body")))
                return True
            except Exception as e:
                print(f"Page load failed (attempt {attempt+1}): {str(e)}")
                if attempt == retries - 1:
                    return False
                time.sleep(2)

    try:
        if not robust_get(url):
            return []
            
        def wait_and_click(xpath, timeout=20):
            try:
                WebDriverWait(driver, timeout).until(
                    EC.element_to_be_clickable((By.XPATH, xpath))).click()
                time.sleep(0.5)
            except Exception as e:
                raise Exception(f"Click failed on {xpath}: {str(e)}")

        # Date picker interaction
        wait_and_click("/html/body/c-wiz[2]/div/div[2]/div[1]/div[2]/span[2]/c-wiz/c-wiz/div/div/div/div/div/div/div/section/div[1]/div[1]/div/div[2]/div[1]/div/input")
        time.sleep(2)
        
        # Date selection
        for date_xpath in [
            f"//*[@data-iso='{checkin_date}']/div/div[1]",
            f"//*[@data-iso='{checkout_date}']/div/div[1]"
        ]:
            wait_and_click(date_xpath)

        # Final confirmation
        wait_and_click("/html/body/c-wiz[2]/div/div[2]/div[1]/div[2]/span[2]/c-wiz/c-wiz/div/div/div/div/div/div/div/section/div[1]/div[1]/div[2]/div/div[2]/div[4]/div[2]/button[2]/span")

        # Verify update
        WebDriverWait(driver, 20).until(
            EC.presence_of_element_located((By.XPATH, "//*[@id='prices']/span"))
        )
        
        resultant_url = driver.current_url
        
    except Exception as e:
        print(f"Error during browser interaction: {str(e)}")
        return []
    finally:
        driver.quit()
        
    try:   
        response = requests.get(resultant_url)
        html_content = response.content
        parser = etree.HTMLParser()
        tree = etree.HTML(html_content, parser=parser)

        outer_div_xpath = '//*[@id="prices"]/c-wiz/c-wiz/div/div/div/div/div/div/div/section/div[2]/c-wiz/div/div/div[2]/div[2]/span'
        outer_div = tree.xpath(outer_div_xpath)

        prices = []
        
        if outer_div:
            child_divs = outer_div[0].xpath('.//div')
            for index, child_div in enumerate(child_divs, start=1):
                website_name_div_xpath = './/div/div/div/div/a/div/div[1]/div/span[1]/span'
                website_name_div = child_div.xpath(website_name_div_xpath)
                
                price_div_xpath = './/div/div/div/div/a/div/div[2]/span/span/span/span[3]'
                price_div = child_div.xpath(price_div_xpath)
                
                if website_name_div and price_div:
                    website_name = website_name_div[0].xpath('.//text()')
                    price = price_div[0].xpath('.//text()')
                    result = '' + ' '.join(website_name).strip() + ':' + ' '.join(price).strip()[:]
                    prices.append(result)
        return prices
    except Exception as e:
        print(f"Error during HTML parsing: {str(e)}")
        return []
        
        
# if __name__=='__main__':
#     app.run(host="0.0.0.0", port="8080")
    