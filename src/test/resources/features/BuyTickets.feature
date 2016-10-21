Feature: Aankoop van een ticket voor de film de Lion King van tien uur  

  Background: 
    Given Ik ben ingelogd
    And Ik ben op de home pagina van de cerioscoop
    And Ik selecteer de voorstelling de Lion King die begint om tien uur
  
  @BuyTickets
  Scenario: Koop een ticket voor de Lion King die begint om tien uur
    When Vul ik het aantal tickets in
    And Druk ik op de knop koop
    Then Check ik of het betaalscherm in het scherm staat
    And Log ik uit
	
	@BuyTickets
	Scenario: Er zit een maximum aan het aantal tickets dat kan worden gekocht
		When Vul ik het aantal tickets in dat groter is dan het maximale aantal tickets
    And Druk ik op de knop koop
    Then Check ik of er een melding "Maximum number of tickets exceeded!" verschijnt
    And Log ik uit
	
	@BuyTickets	
	Scenario: Minimaal één ticket moet worden gekocht
	  When Vul ik het aantal tickets in dat kleiner is dan één ticket
    And Druk ik op de knop koop
    Then Check ik of er een melding "You have to buy at least one ticket!" verschijnt
    And Log ik uit
	
	@BuyTickets
	Scenario: Het aantal tickets mag het aantal beschikbare plaatsen niet overschrijden.
    When Vul ik het aantal tickets in dat het aantal beschikbare plaatsen overschrijd
    And Druk ik op de knop koop
    Then Check ik of er een melding "There are not so many places available!" verschijnt
    And Log ik uit
