# 0_winner_quest

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

try:
    # I found that directing to the filepath leads to CORS policy error: fetch at ... from origin 'null'
    directory = "http://127.0.0.1:8081/"
    driver.get(directory)
    time.sleep(1)
    driver.find_element(By.ID, "scenario-4").click()
    time.sleep(1)
    driver.find_element(By.ID, "start-button").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # Q4 is drawn
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click()
    time.sleep(1)

    # create stages
    driver.find_elements(By.CLASS_NAME, "F50")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_elements(By.CLASS_NAME, "F70")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P2 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click()
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "E30")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P3 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click()
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P4 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click()
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # awarding shields
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # player 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(1)


    # asserts
    driver.find_element(By.ID, "show-hands").click()
    time.sleep(1)
    shields = driver.find_elements(By.CLASS_NAME, "shield-count")
    p1Shields = shields[0].get_attribute("innerHTML")
    p2Shields = shields[1].get_attribute("innerHTML")
    p3Shields = shields[2].get_attribute("innerHTML")
    p4Shields = shields[3].get_attribute("innerHTML")
    assert (p1Shields == '0')
    assert (p2Shields == '0')
    assert (p3Shields == '0')
    assert (p4Shields == '0')

    p1Hand = driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
    p2Hand = driver.find_element(By.ID, "p2-hand").get_attribute("innerHTML")
    p3Hand = driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
    p4Hand = driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")

    assert (p1Hand == "P1: F15,D5,D5,D5,D5,S10,S10,S10,H10,H10,H10,H10")
    assert (p2Hand == "P2: F5,F5,F10,F15,F15,F20,F20,F25,F30,F30,F40")
    assert (p3Hand == "P3: F5,F5,F10,F15,F15,F20,F20,F25,F25,F30,F40,L20")
    assert (p4Hand == "P4: F5,F5,F10,F15,F15,F20,F20,F25,F25,F30,F50,E30")


finally:
    driver.quit()