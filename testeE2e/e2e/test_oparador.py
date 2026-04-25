from playwright.sync_api import Page, expect

def test_operador(page: Page):
    page.goto("/adm")
    page.pause()
    page.fill("input[name=\"email\"]","op@email.com" )
    page.pause()
    page.fill("input[name=\"senha\"]", "12345678")
    page.pause()
    page.get_by_role("button", name="Logar").click()
    page.pause()
    page.get_by_role("link", name="Cadastrar cliente").click()
    page.pause()
    page.fill("input[name=\"nome\"]","gabriel passos")
    page.fill("input[name=\"apelido\"]","gabriel louco")
    page.fill("input[name=\"telefone\"]","5199544888")
    page.fill("input[name=\"email\"]","gabrieldospa1109@gmail.com")
    page.fill("input[name=\"senha\"]","12345678")
    expect(page).to_have_title("Caderninho")