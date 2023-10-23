export class TrailPage{
    clickOnButton(buttonText:string){
        cy.contains('button',buttonText).click()
    }
    checkIfCorrectNumberOfRows(count:number){
        cy.get('tbody tr').should('have.length.gte', count);
        
    }
    checkIfCorrectData(data:string){
        cy.get('tbody tr').eq(0).find('td').eq(0).should('contain', data);
    }
}