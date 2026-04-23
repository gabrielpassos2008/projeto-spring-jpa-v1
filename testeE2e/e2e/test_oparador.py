from playwright.sync_api import Page, expect

def test_operador(page: Page):
    page.goto("/adm")
    page.pause()
    expect(page).to_have_title("Document")