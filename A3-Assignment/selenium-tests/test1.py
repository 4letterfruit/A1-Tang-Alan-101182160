import unittest
from webbrowser import Chrome

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

try:
    directory = "file:///C:/Users/Alan Tang/Desktop/Git/A1-Tang-Alan-101182160/A3-Assignment/frontend/index.html"

    driver.get(directory)

    time.sleep(2)

    x_button = driver.find_element(By.ID, "start-button")
    x_button.click()
    time.sleep(1)

    x_button = driver.find_elements(By.CSS_SELECTOR, "#id .class")
    assert (True == True)
finally:
    driver.quit()