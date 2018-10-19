# stripe

Para executar o projeto de testes é necessário ter "instalado" Maven 3.5 + e Java JRE 7+ e as devidas variaveis de ambientes configuradas (Linux/Windows) - Dado que a premissa acima seja atendida:

1- Realizar clone do projeto em uma pasta local `git@github.com:daimacedo/stripe.git`

2- Entrar na pasta do projeto `stripe` e rodar o comando `mvn clean install`

3- Após a build terminar (provavelmente vai terminar com falha e explicarei os motivos abaixo) rodar o seguinte comando para gerar o relatório: `mvn cluecumber-report:reporting`

4: O relatório estará disponivel dentro do diretório: `target/cucumber-reports/formated-report`, o melhor jeito de visualizar é  abrir o arquivo index.html através do explorador do S.O. e navegar pelo report através do browser.

Notas:

- Os testes de enviar CV falharam, pois o site trava com sistema de captcha a execução de testes automaticos no navegador. Para facilitar essa visualização do erro, a aplicação vai tirar um print quando a execução falhar, na imagem vai ser possivel visualizar o captcha, que é o que impede o teste de seguir com o login. Diretório das evidências dos erros: `target/screenshots`

- Os testes foram gerados utilizando DataTable do cucumber, deste modo, foi escrito somente um cenário de validações de campos obrigatorios, alterando o parametro de "field required", por isso no report terão cenários com o mesmo nome, mas ao navegar dentro deles, poderá reparar no step que o nome do campo verificado como required é diferente em cada um deles.

Nota: Vale ressaltar que realizar testes em um sistema de produção, onde travas de segurança e massa de dados não tão consistente (pois pode mudar de repente), dificulta a automação, o ideal é realizar a automação em um ambiente com maior controle de dados, com conexao ao banco para gerar massa pertinente ao teste e também poder matar essa massa ao final da execução do teste. 

Nota2: Logado no sistema tem varias features legais para automatizar que eu poderia ter escolhido para realizar o teste, mas como comentei, o site tem sistema de captcha o que impede que o login seja realizado de modo automatico. (E se em algum momento a gente conseguir bular o captcha com um robo, o captcha perdeu o sentido rs). - Existem alternativas para ja carregar profiles no firefox através de Capabilities, porém algumas configuraçes seriam necessárias na maquina que fosse executar os testes, então os mesmos quebrariam quando vocês tentassem rodar, por isso não optei por essa abordagem, mas vale ressaltar que esse tipo de configuração é possivel.

Bem, qualquer coisa, me chamem :)
