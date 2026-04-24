from playwright.sync_api import Page, expect
import time

def test_login(page):
    page.goto("http://127.0.0.1:5051/login")
    page.wait_for_timeout(500)

    page.fill('input[name="email"]', "1@1")
    page.wait_for_timeout(500)

    page.fill('input[name="senha"]', "1")
    page.wait_for_timeout(500)

    page.get_by_role("button", name="Login").click()
    page.wait_for_timeout(500)

    page.get_by_role("link", name="Estacionamentos Visualize e").click()
    page.wait_for_timeout(500)

    page.get_by_role("link", name="Estacionamento 3 Acessar →").click()
    page.wait_for_timeout(500)

    page.get_by_role("link", name="Cancela 1 Tabela vinculada").click()
    page.wait_for_timeout(500)

    page.get_by_role("link", name="Sincronizar visor").click()
    page.wait_for_timeout(500)
    

    #nova pagina
    aba2 = page.context.new_page()
    aba2.goto("http://127.0.0.1:5051/visor")

    token = aba2.locator("#token-box div").first.text_content().strip()

    aba2.wait_for_timeout(2000)

    page.bring_to_front()

    page.goto("http://127.0.0.1:5051/sincronizar?estacionamento_id=3&cancela_id=7")
    page.wait_for_timeout(1000)

    page.get_by_label("Token do Visor").fill(token)
    page.wait_for_timeout(2000)

    

    

    assert page.url == "http://127.0.0.1:5051/"
    
    
    