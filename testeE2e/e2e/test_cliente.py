from playwright.sync_api import Page, expect
import time
def test_cliente(page: Page):
    page.goto("/")
    page.wait_for_timeout(1000)
    expect(page).to_have_title("Document")
    page.wait_for_timeout(1000)
    page.fill("input[name=\"email\"]","gabrieldospa1109@gmail.com")
    page.wait_for_timeout(1000)
    page.fill("input[name=\"senha\"]","12345678")
    page.wait_for_timeout(1000)
    page.get_by_role("button", name="Logar").click()
    page.wait_for_timeout(1000)
    expect(page).to_have_url("http://127.0.0.1:8080/")
    
    
    