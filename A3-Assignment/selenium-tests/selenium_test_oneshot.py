import unittest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
import time


class GameTest(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

        directory = "http://127.0.0.1:8081/"
        cls.driver.get(directory)
        cls.driver.maximize_window()
        time.sleep(2)

    @classmethod
    def tearDownClass(cls):
        cls.driver.quit()

    def test_A1_Scenario(self):
        self.driver.find_element(By.ID, "restart").click()
        time.sleep(2)

        self.driver.find_element(By.ID, "scenario-1").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "start-button").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # Q4 drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "no").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)

        # create stages
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F40")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P3 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attacks 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P1 attacks 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)


        # P3 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P1 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P3 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P3 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "E30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # award shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 prompt trim
        self.driver.find_elements(By.CLASS_NAME, "action-button")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "action-button")[1].click()
        self.driver.find_elements(By.CLASS_NAME, "action-button")[2].click()
        self.driver.find_elements(By.CLASS_NAME, "action-button")[3].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # self.assertTrues
        self.driver.find_element(By.ID, "show-hands").click()
        shields = self.driver.find_elements(By.CLASS_NAME, "shield-count")
        p1Shields = shields[0].get_attribute("innerHTML")
        p2Shields = shields[1].get_attribute("innerHTML")
        p3Shields = shields[2].get_attribute("innerHTML")
        p4Shields = shields[3].get_attribute("innerHTML")
        self.assertTrue (p1Shields == '0')
        self.assertTrue (p2Shields == '0')
        self.assertTrue (p3Shields == '0')
        self.assertTrue (p4Shields == '4')

        p1Hand = self.driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
        p3Hand = self.driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
        p4Hand = self.driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")
        p2HandSize = self.driver.find_elements(By.CLASS_NAME, "card-count")[1].get_attribute("innerHTML")

        self.assertTrue (p1Hand == "P1: F5,F10,F15,F15,F30,H10,B15,B15,L20")
        self.assertTrue (p3Hand == "P3: F5,F5,F15,F30,S10")
        self.assertTrue (p4Hand == "P4: F15,F15,F40,L20")
        self.assertTrue (p2HandSize == '12')

    def test_2_Winner_2_Game(self):
        self.driver.find_element(By.ID, "restart").click()
        time.sleep(2)

        self.driver.find_element(By.ID, "scenario-2").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "start-button").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # Q4 is drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)

        # build stages
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P3 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # award shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P1 trim
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F15")[1].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 turn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # Q3 drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "no").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)

        # create stages
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P1 refuses
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "no").click()
        time.sleep(1)

        # P2 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "E30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "E30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # award shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # player 3 trim
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F25")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # self.assertTrues
        self.driver.find_element(By.ID, "show-hands").click()
        time.sleep(1)
        shields = self.driver.find_elements(By.CLASS_NAME, "shield-count")
        p1Shields = shields[0].get_attribute("innerHTML")
        p2Shields = shields[1].get_attribute("innerHTML")
        p3Shields = shields[2].get_attribute("innerHTML")
        p4Shields = shields[3].get_attribute("innerHTML")
        self.assertTrue (p1Shields == '0')
        self.assertTrue (p2Shields == '7')
        self.assertTrue (p3Shields == '0')
        self.assertTrue (p4Shields == '7')

        p1Hand = self.driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
        p2Hand = self.driver.find_element(By.ID, "p2-hand").get_attribute("innerHTML")
        p3Hand = self.driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
        p4Hand = self.driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")

        self.assertTrue (p1Hand == "P1: F15,F15,F20,F20,F20,F20,F25,F25,F30,H10,B15,L20")
        self.assertTrue (p2Hand == "P2: F10,F15,F15,F25,F30,F40,F50,L20,L20")
        self.assertTrue (p3Hand == "P3: F20,F40,D5,D5,S10,H10,H10,H10,H10,B15,B15,L20")
        self.assertTrue (p4Hand == "P4: F15,F15,F20,F25,F30,F50,F70,L20,L20")

        mainText = self.driver.find_element(By.ID, "mainText").get_attribute("innerHTML")
        print(mainText)
        self.assertTrue (mainText == "Congratulations to our Winners: [2, 4]")


    def test_1_winner_with_events(self):
        self.driver.find_element(By.ID, "restart").click()
        time.sleep(2)

        self.driver.find_element(By.ID, "scenario-3").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "start-button").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # Q4 drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
    
        # P1 build stages
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P4 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P4 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P4 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P4 attack 4
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # award shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P1 trim
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F5")[1].click()
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F10")[1].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
    
        # P2 turn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # Plague
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 turn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # Prosperity
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        #discard player 3
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        #player 4 turn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # prompted to discard from prosperity
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        #queen's favor
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F25")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F30")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        #player 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # discard from prosperity
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        #q3 drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
    
        # stage creation
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
    
        # player 2 discard + attack
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # discard from prosperity
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        # accept
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P4 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 2
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P2 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # P3 attack 3
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "E30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # awarding shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # p1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F15")[1].click()
        self.driver.find_elements(By.CLASS_NAME, "F15")[2].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
    
        # winners are declared
        # do asserts
        self.driver.find_element(By.ID, "show-hands").click()
        time.sleep(1)
    
        shields = self.driver.find_elements(By.CLASS_NAME, "shield-count")
        p1Shields = shields[0].get_attribute("innerHTML")
        p2Shields = shields[1].get_attribute("innerHTML")
        p3Shields = shields[2].get_attribute("innerHTML")
        p4Shields = shields[3].get_attribute("innerHTML")
        assert (p1Shields == '0')
        assert (p2Shields == '5')
        assert (p3Shields == '7')
        assert (p4Shields == '4')
    
        p1Hand = self.driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
        p2Hand = self.driver.find_element(By.ID, "p2-hand").get_attribute("innerHTML")
        p3Hand = self.driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
        p4Hand = self.driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")
    
        assert (p1Hand == "P1: F25,F25,F35,D5,D5,S10,S10,S10,S10,H10,H10,H10")
        assert (p2Hand == "P2: F15,F25,F30,F40,S10,S10,S10,H10,E30")
        assert (p3Hand == "P3: F10,F25,F30,F40,F50,S10,S10,H10,H10,L20")
        assert (p4Hand == "P4: F25,F25,F30,F50,F70,D5,D5,S10,S10,B15,L20")
    
        mainText = self.driver.find_element(By.ID, "mainText").get_attribute("innerHTML")
        assert (mainText == "Congratulations to our Winners: [3]")


    def test_0_winner_quest(self):
        self.driver.find_element(By.ID, "restart").click()
        time.sleep(2)

        self.driver.find_element(By.ID, "scenario-4").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "start-button").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # Q4 is drawn
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)

        # create stages
        self.driver.find_elements(By.CLASS_NAME, "F50")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F70")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "D5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "S10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "H10")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "B15")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "L20")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P2 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_elements(By.CLASS_NAME, "E30")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P3 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F15")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # P4 attack 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "yes").click()
        time.sleep(1)
        #discard
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        #attack
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # awarding shields
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)

        # player 1
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)
        self.driver.find_elements(By.CLASS_NAME, "F5")[0].click()
        self.driver.find_elements(By.CLASS_NAME, "F10")[0].click()
        self.driver.find_element(By.ID, "next").click()
        time.sleep(1)


        # asserts
        self.driver.find_element(By.ID, "show-hands").click()
        time.sleep(1)
        shields = self.driver.find_elements(By.CLASS_NAME, "shield-count")
        p1Shields = shields[0].get_attribute("innerHTML")
        p2Shields = shields[1].get_attribute("innerHTML")
        p3Shields = shields[2].get_attribute("innerHTML")
        p4Shields = shields[3].get_attribute("innerHTML")
        assert (p1Shields == '0')
        assert (p2Shields == '0')
        assert (p3Shields == '0')
        assert (p4Shields == '0')

        p1Hand = self.driver.find_element(By.ID, "p1-hand").get_attribute("innerHTML")
        p2Hand = self.driver.find_element(By.ID, "p2-hand").get_attribute("innerHTML")
        p3Hand = self.driver.find_element(By.ID, "p3-hand").get_attribute("innerHTML")
        p4Hand = self.driver.find_element(By.ID, "p4-hand").get_attribute("innerHTML")

        assert (p1Hand == "P1: F15,D5,D5,D5,D5,S10,S10,S10,H10,H10,H10,H10")
        assert (p2Hand == "P2: F5,F5,F10,F15,F15,F20,F20,F25,F30,F30,F40")
        assert (p3Hand == "P3: F5,F5,F10,F15,F15,F20,F20,F25,F25,F30,F40,L20")
        assert (p4Hand == "P4: F5,F5,F10,F15,F15,F20,F20,F25,F25,F30,F50,E30")


if __name__ == "__main__":
    unittest.main()