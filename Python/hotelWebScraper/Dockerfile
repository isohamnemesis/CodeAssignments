# Use Python image with full Debian base
FROM python:3.10-bullseye

# Install system dependencies including Chromium
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    ca-certificates \
    unzip \
    chromium \
    chromium-driver \
    fonts-liberation \
    libasound2 \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libatspi2.0-0 \
    libcairo2 \
    libcups2 \
    libcurl4 \
    libdbus-1-3 \
    libdrm2 \
    libexpat1 \
    libgbm1 \
    libglib2.0-0 \
    libgtk-3-0 \
    libnspr4 \
    libnss3 \
    libpango-1.0-0 \
    libx11-6 \
    libxcb1 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxkbcommon0 \
    libxrandr2 \
    xdg-utils \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Set up Chrome alternatives (Chromium will be used)
RUN update-alternatives --install /usr/bin/google-chrome google-chrome /usr/bin/chromium 100

# Set environment variables
ENV DISPLAY=:99 \
    CHROME_BIN=/usr/bin/chromium \
    CHROME_PATH=/usr/bin/chromedriver \
    CHROMIUM_FLAGS="--no-sandbox --headless --disable-gpu --remote-debugging-port=9222"

# Verify installations
# RUN chromium --version && chromedriver --version

# Set up working directory
WORKDIR /app

# Copy requirements and install Python dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy application code
COPY . .

# Run the application
CMD ["gunicorn", "--bind", "0.0.0.0:8080", "--workers", "4", "app:app"]