# 1 winner game with events
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
    time.sleep(0.5)

    driver.find_element(By.ID, "scenario-3").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "start-button").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # Q4 drawn
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)

    # P1 build stages
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P4 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P4 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P4 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 4
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 4
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P4 attack 4
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # award shields
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P1 trim
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_elements(By.CLASS_NAME, "F5")[1].click()
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    driver.find_elements(By.CLASS_NAME, "F10")[1].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)


    # P2 turn
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    # Plague
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 turn
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    # Prosperity
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    #discard player 3
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    #player 4 turn
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # prompted to discard from prosperity
    driver.find_elements(By.CLASS_NAME, "F20")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    #queen's favor
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F25")[0].click()
    driver.find_elements(By.CLASS_NAME, "F30")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    #player 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    # discard from prosperity
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    #q3 drawn
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)

    # stage creation
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F20")[0].click()
    driver.find_elements(By.CLASS_NAME, "D5")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)


    # player 2 discard + attack
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    # discard from prosperity
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    # accept
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F5")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P4 attack 1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #discard
    driver.find_elements(By.CLASS_NAME, "F20")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "H10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 2
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "B15")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P2 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "L20")[0].click()
    driver.find_elements(By.CLASS_NAME, "S10")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # P3 attack 3
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "yes").click()
    time.sleep(0.5)
    #attack
    driver.find_elements(By.CLASS_NAME, "E30")[0].click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # awarding shields
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # p1
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)
    driver.find_elements(By.CLASS_NAME, "F15")[0].click()
    driver.find_elements(By.CLASS_NAME, "F15")[1].click()
    driver.find_elements(By.CLASS_NAME, "F15")[2].click()
    driver.find_element(By.ID, "next").click()
    time.sleep(0.5)

    # winners are declared
    # do asserts
    driver.find_element(By.ID, "show-hands").click()
    time.sleep(0.5)

    shields = driver.find_elements(By.CLASS_NAME, "shield-count")
    p1Shields = shields[0].get_attribute("innerHTML")
    p2Shields = shields[1].get_attribute("innerHTML")
    p3Shields = shields[2].get_attribute("innerHTML")
    p4Shields = shields[3].get_attribute("innerHTML")
    assert (p1Shields == '0')
    assert (p2Shields == '5')
    assert (p3Shields == '7')
    assert (p4Shields == '4')

    p1Hand = driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
    p2Hand = driver.find_element(By.ID, "p2-hand").get_attribute("innerHTML")
    p3Hand = driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
    p4Hand = driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")

    assert (p1Hand == "P1: F25,F25,F35,D5,D5,S10,S10,S10,S10,H10,H10,H10")
    assert (p2Hand == "P2: F15,F25,F30,F40,S10,S10,S10,H10,E30")
    assert (p3Hand == "P3: F10,F25,F30,F40,F50,S10,S10,H10,H10,L20")
    assert (p4Hand == "P4: F25,F25,F30,F50,F70,D5,D5,S10,S10,B15,L20")

    mainText = driver.find_element(By.ID, "mainText").get_attribute("innerHTML")
    assert (mainText == "Congratulations to our Winners: [3]")





finally:
    driver.quit()