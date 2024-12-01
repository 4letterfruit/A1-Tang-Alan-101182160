import unittest
from webbrowser import Chrome

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

try:
    driver.get("file:///Users/Alan Tang/Desktop/Git/A1-Tang-Alan-101182160/A3-Assignment/frontend/index.html")
    print("run")
finally:
    driver.quit()