export class AppPage{
    navigateToHomePage(){
        cy.visit('/');
    }
    checkForTitle(){
        cy.get('app-root header').contains("GALWAY HIKING TRAILS")
    }
}