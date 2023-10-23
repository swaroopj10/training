import {AppPage} from "cypress/support/app.po"
import { TrailPage } from "cypress/support/trail.po";

describe('Additional Filters', () => {
  let app:AppPage = new AppPage();
  let trail:TrailPage = new TrailPage();

  it('should show correct data Name Reverse Order',()=>{
      app.navigateToHomePage()
      trail.clickOnButton("Get Trails by Name")
      trail.clickOnButton("Get name Trails by Reverse Order")
      trail.checkIfCorrectData("Slieve Carran Brown and Yellow Trails")
  })

  it('should show correct data Length',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Length")
    trail.clickOnButton("Get length Trails by Reverse Order")
    trail.checkIfCorrectData("Slieve Carran Brown and Yellow Trails");
  })

  it('should show correct data Time to Complete Reverse Order',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Time to Complete")
    trail.clickOnButton("Get time-to-complete Trails by Reverse Order")
    trail.checkIfCorrectData("Slieve Carran Brown and Yellow Trails");
  })

  it('should show correct data Driving Time Reverse Order',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Driving Time from Galway City")
    trail.clickOnButton("Get driving-time Trails by Reverse Order")
    trail.checkIfCorrectData("Slieve Carran Brown and Yellow Trails");
  })

  it('should show correct data Difficulty Reverse Order',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Difficulty")
    trail.clickOnButton("Get difficulty Trails by Reverse Order")
    trail.checkIfCorrectData("Slieve Carran Brown and Yellow Trails");
  })




  it('should show correct data Name Top 5',()=>{
    app.navigateToHomePage()
    trail.clickOnButton("Get Trails by Name")
    trail.clickOnButton("Get Top 5 name Trails")
    trail.checkIfCorrectData("Caher River Loop")
})

it('should show correct data Length Top 5',()=>{
  app.navigateToHomePage()
  trail.clickOnButton("Get Trails by Length")
  trail.clickOnButton("Get Top 5 length Trails")
  trail.checkIfCorrectData("Caher River Loop");
})

it('should show correct data Time to Complete Top 5',()=>{
  app.navigateToHomePage()
  trail.clickOnButton("Get Trails by Time to Complete")
  trail.clickOnButton("Get Top 5 time-to-complete Trails")
  trail.checkIfCorrectData("Caher River Loop");
})

it('should show correct data Driving Time Top 5',()=>{
  app.navigateToHomePage()
  trail.clickOnButton("Get Trails by Driving Time from Galway City")
  trail.clickOnButton("Get Top 5 driving-time Trails")
  trail.checkIfCorrectData("Caher River Loop");
})

it('should show correct data Difficulty Top 5',()=>{
  app.navigateToHomePage()
  trail.clickOnButton("Get Trails by Difficulty")
  trail.clickOnButton("Get Top 5 difficulty Trails")
  trail.checkIfCorrectData("Caher River Loop");
})
  
})
