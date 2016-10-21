Feature: Testen van de film informatie

  #Scenario: Test TheLionKing informatie
  # Given Ik ben op de home pagina van de cerioscoop
  #  When Ik klik op TheLionKing
  #  Then Check ik of ik op TheLionKing scherm ben
  #  And Sluit ik de browser
  #Scenario: Test Snatch informatie
  #  Given Ik ben op de home pagina van de cerioscoop
  #  When Ik klik op Snatch
  #  Then Check ik of ik op Snatch scherm ben
  #  And Sluit ik de browser
  #Scenario: Test TheLegendOfTarzan informatie
  #  Given Ik ben op de home pagina van de cerioscoop
  #  When Ik klik op TheLegendOfTarzan
  #  Then Check ik of ik op TheLegendOfTarzan scherm ben
  #  And Sluit ik de browser
  @MoviePresentation
  Scenario Outline: Test film informatie
    Given Ik ben op de home pagina
    When Ik klik op <FilmLink>
    Then Check ik of ik op <FilmTitel> scherm ben

    Examples: 
      | FilmLink               | FilmTitel            |
      | movietitlebymovieid6   | The Lion King        |
      | movietitlebymovieid7   | Snatch               |
      | movietitlebymovieid1   | The Legend of Tarzan |
