import {AppPage} from "cypress/support/app.po"
import { TrailPage } from "cypress/support/trail.po";

describe('Page Component and Main Button', () => {
  let app:AppPage = new AppPage();
  let trail:TrailPage = new TrailPage();

  it('Visits the initial project page', () => {
    cy.visit('/')
    cy.contains('GALWAY HIKING TRAIL')
  })
  it('Heading should be shown', () => {
    app.navigateToHomePage()
    app.checkForTitle()
  })

  it('should show correct data',()=>{
      app.navigateToHomePage()
      trail.clickOnButton("Get All Trails")
      trail.checkIfCorrectData("Caher River Loop")
  })

  it('should show correct number of rows for table',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get All Trails")
    trail.checkIfCorrectNumberOfRows(5);
  })

  it('should show correct data Name',()=>{
      app.navigateToHomePage()
      trail.clickOnButton("Get Trails by Name")
      trail.checkIfCorrectData("Caher River Loop")
  })

  it('should show correct number of rows Length',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Length")
    trail.checkIfCorrectNumberOfRows(5);
  })

  it('should show correct data Length',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Length")
    trail.checkIfCorrectData("Rinville Park Loop");
  })

  it('should show correct number of rows Time to Complete',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Time to Complete")
    trail.checkIfCorrectNumberOfRows(5);
  })

  it('should show correct data Time to Complete',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Time to Complete")
    trail.checkIfCorrectData("Rinville Park Loop");
  })

  it('should show correct number of rows Driving Time',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Driving Time from Galway City")
    trail.checkIfCorrectNumberOfRows(5);
  })

  it('should show correct data Driving Time',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Driving Time from Galway City")
    trail.checkIfCorrectData("Cúirt Literary Trail");
  })

  it('should show correct number of rows Difficulty',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Difficulty")
    trail.checkIfCorrectNumberOfRows(5);
  })

  it('should show correct data Difficulty',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Difficulty")
    trail.checkIfCorrectData("Rinville Park Loop");
  })

  
})
