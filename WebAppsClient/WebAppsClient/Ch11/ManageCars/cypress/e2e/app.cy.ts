import { AppPageObject } from "cypress/support/app.po";
import { TablePageObject } from "cypress/support/table.po";

let appPage : AppPageObject = new AppPageObject();
let tablePage : TablePageObject = new TablePageObject();

describe('My First Test', () => {
  beforeEach(() => {
    appPage.navigateToHomePage();
  })

  it('Visits the initial project page', () => {
    appPage.checkTitle('Cars World app is running!')
  })

  it('checks title of the table', () => {
    appPage.checkTitle('Cars List')
  })

  it('if getAllCars button works', () => {
    cy.get('#getCars').click();
    cy.get('table').should('be.visible');
  })

  it('if getAllCars button works', () => {
    cy.get('#price').click();
    cy.get('table').should('be.visible');
  })

  it('if getAllCars button works', () => {
    cy.get('#doors').click();
    cy.get('table').should('be.visible');
  })

  it('if getAllCars button works', () => {
    cy.get('#year').click();
    cy.get('table').should('be.visible');
  })

  it('contains header for table', () => {
    appPage.navigateToHomePage();
    tablePage.checkBookTableHeader('Cars List');
  });

  it('contains table of cars', () => {
    appPage.navigateToHomePage();
    tablePage.getBookTableRows().should('have.length.gte', 1);
  });

  it('contains correct name of 1st Make', () => {
    appPage.navigateToHomePage();
    tablePage.getBookTableFirstRowTitleColumn().should('eq', 'Tesla');
  });

  it('contains last book', () => {
    appPage.navigateToHomePage();
    tablePage.getBookTableLastRowTitleColumn().should('exist');
  });
})
