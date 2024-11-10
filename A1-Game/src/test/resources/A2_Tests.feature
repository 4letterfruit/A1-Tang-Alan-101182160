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
    When Q4 is drawn
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
    And Player 1 has 0 shields and their hand is ["F5 F10 F15 F15 F30 H10 B15 B15 L20"]
    And Player 3 has 0 shields and their hand is ["F5 F5 F15 F30 S10"]
    And Player 4 has 4 shields and their hand is ["F15 F15 F40 L20"]
    And Player 2 replenishes 9 + 4 cards and trims to 12 cards

