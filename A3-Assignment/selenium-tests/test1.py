import unittest
from webbrowser import Chrome

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
    driver.find_element(By.ID, "scenario-1").click()
    time.sleep(1)
    driver.find_element(By.ID, "start-button").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    # Q4 drawn
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "no").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click()
    time.sleep(1)

    # create stages
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_elements(By.CLASS_NAME, "F40")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P3 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)


    # P4 attacks 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)




    # P1 attacks 1
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)


    # P3 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P4 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P1 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P3 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P4 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P3 attack 4
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P4 attack 4
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "yes").click();
    time.sleep(1)
    #attack
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    driver.find_elements(By.CLASS_NAME, "E30")[0].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # award shields
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # P2 prompt trim
    driver.find_elements(By.CLASS_NAME, "action-button")[0].click()
    driver.find_elements(By.CLASS_NAME, "action-button")[1].click()
    driver.find_elements(By.CLASS_NAME, "action-button")[2].click()
    driver.find_elements(By.CLASS_NAME, "action-button")[3].click()
    time.sleep(1)
    driver.find_element(By.ID, "next").click()
    time.sleep(1)

    # asserts
    driver.find_element(By.ID, "show-hands").click()
    shields = driver.find_elements(By.CLASS_NAME, "shield-count")
    p1Shields = shields[0].get_attribute("innerHTML")
    p2Shields = shields[1].get_attribute("innerHTML")
    p3Shields = shields[2].get_attribute("innerHTML")
    p4Shields = shields[3].get_attribute("innerHTML")
    assert (p1Shields == '0')
    assert (p2Shields == '0')
    assert (p3Shields == '0')
    assert (p4Shields == '4')

    p1Hand = driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
    p3Hand = driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
    p4Hand = driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")
    p2HandSize = driver.find_elements(By.CLASS_NAME, "card-count")[1].get_attribute("innerHTML")

    assert (p1Hand == "P1: F5,F10,F15,F15,F30,H10,B15,B15,L20")
    assert (p3Hand == "P3: F5,F5,F15,F30,S10")
    assert (p4Hand == "P4: F15,F15,F40,L20")
    assert (p2HandSize == '12')



finally:
    driver.quit()