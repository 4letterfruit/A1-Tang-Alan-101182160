Feature: A2_Tests

  Scenario: A1_Scenario
    #1
    Given the game has started
    #2
    And Player 1's hand is ["F5 F5 F15 F15 D5 S10 S10 H10 H10 B15 B15 L20"]
    And Player 2's hand is ["F5 F5 F15 F15 F40 D5 S10 H10 H10 B15 B15 E30"]
    And Player 3's hand is ["F5 F5 F5 F15 D5 S10 S10 S10 H10 H10 B15 L20"]
    And Player 4's hand is ["F5 F15 F15 F40 D5 D5 S10 H10 H10 B15 L20 E30"]
    # 3
    When "Q4" is drawn by Player 1
    # 4, 5
    Then Player 2 sponsors the "Q4" and builds "[F5 H10],[F15 S10],[F15 D5 B15],[F40 B15]"
    #6
    And Player 1 participates in stage 1 - draws a "F30" - discards a "F5"
    And Player 3 participates in stage 1 - draws a "S10" - discards a "F5"
    And Player 4 participates in stage 1 - draws a "B15" - discards a "F5"
    And Player 1 builds attack ["D5 S10"] for stage 1 and wins
    And Player 3 builds attack ["S10 D5"] for stage 1 and wins
    And Player 4 builds attack ["D5 H10"] for stage 1 and wins
    #7
    And Player 1 participates in stage 2 - draws a "F10"
    And Player 3 participates in stage 2 - draws a "L20"
    And Player 4 participates in stage 2 - draws a "L20"
    And Player 1 builds attack ["H10 S10"] for stage 2 and loses
    And Player 3 builds attack ["B15 S10"] for stage 2 and wins
    And Player 4 builds attack ["H10 B15"] for stage 2 and wins
    #8
    And Player 3 participates in stage 3 - draws a "B15"
    And Player 4 participates in stage 3 - draws a "S10"
    And Player 3 builds attack ["L20 H10 S10"] for stage 3 and wins
    And Player 4 builds attack ["B15 S10 L20"] for stage 3 and wins
    #9
    And Player 3 participates in stage 4 - draws a "F30"
    And Player 4 participates in stage 4 - draws a "L20"
    And Player 3 builds attack ["B15 H10 L20"] for stage 4 and loses
    And Player 4 builds attack ["D5 S10 L20 E30"] for stage 4 and wins
    #10 + resolution
    And Player 1 has 0 shields
    And Player 1's hand is now ["F5 F10 F15 F15 F30 H10 B15 B15 L20"]
    And Player 3 has 0 shields
    And Player 3's hand is now ["F5 F5 F15 F30 S10"]
    And Player 4 has 4 shields
    And Player 4's hand is now ["F15 F15 F40 L20"]
    And Player 2 replenishes 9 + 4 cards and trims hand

  Scenario: 2Winner_Game_2Winner_Quest
    #1
#    F15/4,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/0,H10/8,S10/8,B15/8,L20/2,E30/2
    Given the game has started
    And Player 1's hand is ["F5 F5 F10 F10 F15 F20 F25 S10 S10 H10 H10 L20"]
    And Player 2's hand is ["F5 F5 F10 F15 F20 D5 D5 S10 S10 H10 H10 L20"]
    And Player 3's hand is ["F5 F5 F10 F10 F15 F20 D5 S10 S10 H10 H10 L20"]
    And Player 4's hand is ["F5 F5 F10 F15 F20 D5 D5 S10 S10 H10 H10 L20"]
    When "Q4" is drawn by Player 1
    Then Player 1 sponsors the "Q4" and builds "[F10],[F15],[F20],[F25]"
    #2
    And Player 2 participates in stage 1 - draws a "S10"
    And Player 3 participates in stage 1 - draws a "D5"
    And Player 4 participates in stage 1 - draws a "S10"
    #2+3
    And Player 2 builds attack ["S10"] for stage 1 and wins
    And Player 3 builds attack ["D5"] for stage 1 and loses
    And Player 4 builds attack ["S10"] for stage 1 and wins
    #4
    And Player 2 participates in stage 2 - draws a "S10"
    And Player 4 participates in stage 2 - draws a "S10"
    And Player 2 builds attack ["S10 D5"] for stage 2 and wins
    And Player 4 builds attack ["S10 D5"] for stage 2 and wins

    And Player 2 participates in stage 3 - draws a "S10"
    And Player 4 participates in stage 3 - draws a "S10"
    And Player 2 builds attack ["S10 H10"] for stage 3 and wins
    And Player 4 builds attack ["S10 H10"] for stage 3 and wins

    And Player 2 participates in stage 4 - draws a "H10"
    And Player 4 participates in stage 4 - draws a "H10"
    #5 shields awarded on last stage's win
    And Player 2 builds attack ["L20 H10"] for stage 4 and wins
    And Player 4 builds attack ["L20 H10"] for stage 4 and wins
    And Player 1 replenishes 4 + 4 cards and trims hand

    #6
    And "Q3" is drawn by Player 2
    And Player 3 sponsors the "Q3" and builds "[F5],[F10],[F15]"
    #7
    And Player 1 declines to participate in stage 1
    #8
    And Player 2 participates in stage 1 - draws a "S10"
    And Player 4 participates in stage 1 - draws a "S10"
    And Player 2 builds attack ["D5"] for stage 1 and wins
    And Player 4 builds attack ["D5"] for stage 1 and wins

    And Player 2 participates in stage 2 - draws a "B15"
    And Player 4 participates in stage 2 - draws a "B15"
    And Player 2 builds attack ["H10"] for stage 2 and wins
    And Player 4 builds attack ["H10"] for stage 2 and wins

    And Player 2 participates in stage 3 - draws a "B15"
    And Player 4 participates in stage 3 - draws a "B15"
    And Player 2 builds attack ["B15"] for stage 3 and wins
    And Player 4 builds attack ["B15"] for stage 3 and wins

    # tracking as 4+3 shields
    And Player 2 has 7 shields
    And Player 4 has 7 shields
    And Player "2 4" wins

  Scenario: 1Winner_Game_With_Events
    Given the game has started
    # setup
    And Player 1's hand is ["F5 F5 F5 F5 F10 F10 F15 F15 F20 F20 F25 F25"]
    And Player 2's hand is ["F10 D5 S10 S10 S10 H10 H10 B15 B15 L20 L20 E30"]
    And Player 3's hand is ["F10 D5 S10 S10 S10 H10 H10 B15 B15 L20 L20 E30"]
    And Player 4's hand is ["F10 F10 F20 F20 D5 S10 S10 S10 H10 H10 B15 L20"]
    # event
    When "Q4" is drawn by Player 1
    Then Player 1 sponsors the "Q4" and builds "[F5],[F10],[F15],[F20]"
    # players participate and win
    And Player 2 participates in stage 1 - draws a "D5"
    And Player 3 participates in stage 1 - draws a "D5"
    And Player 4 participates in stage 1 - draws a "D5"
    And Player 2 builds attack ["D5 S10"] for stage 1 and wins
    And Player 3 builds attack ["D5 S10"] for stage 1 and wins
    And Player 4 builds attack ["D5 S10"] for stage 1 and wins
    And Player 2 participates in stage 2 - draws a "S10"
    And Player 3 participates in stage 2 - draws a "S10"
    And Player 4 participates in stage 2 - draws a "S10"
    And Player 2 builds attack ["S10"] for stage 2 and wins
    And Player 3 builds attack ["S10"] for stage 2 and wins
    And Player 4 builds attack ["S10"] for stage 2 and wins
    And Player 2 participates in stage 3 - draws a "F35"
    And Player 3 participates in stage 3 - draws a "F35"
    And Player 4 participates in stage 3 - draws a "F35"
    And Player 2 builds attack ["B15"] for stage 3 and wins
    And Player 3 builds attack ["B15"] for stage 3 and wins
    And Player 4 builds attack ["B15"] for stage 3 and wins
    And Player 2 participates in stage 4 - draws a "F40"
    And Player 3 participates in stage 4 - draws a "F40"
    And Player 4 participates in stage 4 - draws a "F50"
    And Player 2 builds attack ["L20"] for stage 4 and wins
    And Player 3 builds attack ["L20"] for stage 4 and wins
    And Player 4 builds attack ["L20"] for stage 4 and wins
    And Player 1 replenishes 4 + 4 cards and trims hand
    # check shields
    And Player 2 has 4 shields
    And Player 3 has 4 shields
    And Player 4 has 4 shields
    # event cards
    And "E-Plague" is drawn by Player 2
    And Player 2 has 2 shields
    And Player 1 has 12 cards
    And Player 2 has 10 cards
    And Player 3 has 10 cards
    And Player 4 has 10 cards
    And "E-Prosperity" is drawn by Player 3
    And Player 1 has 14 cards
    And Player 1 trims hand
    And Player 1 has 12 cards
    And Player 2 has 12 cards
    And Player 3 has 12 cards
    And Player 4 has 12 cards
    And "E-Queen's Favor" is drawn by Player 4
    And Player 4 has 14 cards
    And Player 4 trims hand
    And Player 4 has 12 cards
    # next stage
    And "Q3" is drawn by Player 1
    And Player 1 sponsors the "Q3" and builds "[F20],[F25],[F30]"
    And Player 2 participates in stage 1
    And Player 3 participates in stage 1
    And Player 4 participates in stage 1
    And Player 2 builds attack ["S10 H10"] for stage 1 and wins
    And Player 3 builds attack ["S10 H10"] for stage 1 and wins
    And Player 4 builds attack ["S10"] for stage 1 and loses
    And Player 2 participates in stage 2
    And Player 3 participates in stage 2
    And Player 2 builds attack ["H10 B15"] for stage 2 and wins
    And Player 3 builds attack ["H10 B15"] for stage 2 and wins
    And Player 2 participates in stage 3
    And Player 3 participates in stage 3
    And Player 2 builds attack ["E30"] for stage 3 and wins
    And Player 3 builds attack ["E30"] for stage 3 and wins
    And Player 1 replenishes 3 + 3 cards and trims hand
    And Player 2 has 5 shields
    And Player 3 has 7 shields
    And Player "3" wins

