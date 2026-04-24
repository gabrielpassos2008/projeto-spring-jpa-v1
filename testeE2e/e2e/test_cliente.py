from playwright.sync_api import Page, expect

def test_cliente(page: Page):
    page.goto("/")
    page.wait_for_timeout(1000)
    expect(page).to_have_title("Document")
    page.wait_for_timeout(1000)
    # Credenciais do DataInitializer (README) — banco vazio no CI só tem este usuário.
    page.fill("input[name=\"email\"]", "cliente@email.com")
    page.wait_for_timeout(1000)
    page.fill("input[name=\"senha\"]", "1234")
    page.wait_for_timeout(1000)
    page.get_by_role("button", name="Logar").click()
    page.wait_for_timeout(1000)
    expect(page).to_have_url("http://127.0.0.1:8080/")

    

    

    
    
    