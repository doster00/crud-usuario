Projeto: Crud Usuários
========================
Author: Bruno Alves
Tecnologias: JSF, Primefaces, Hibernate, H2, JUnit
Sumário: Crud de usuários para teste admissional

Informação
--
Este projeto é para fins de estudo e avaliação relacionada a um processo seletivo.

Requesitos
--
É necessário ter o JDK8 ou superior, Maven 3.0, Apache tomcat 8.5.

Acessando a aplicação
--
URL para acesso: <http://localhost:8080/crud-usuario>
Para realizar o primeiro acesso a aplicação será necessário utilizar um usuário padrão que é cadastrado na primeira vez que a aplicação é iniciada.
e-mail: master@master.com
senha: master

Processo de deploy
--
A aplicação está hospedada no heroku e pode ser acessada pelo link <https://crud-usuario-app.herokuapp.com/>. Foi criado um "pipeline" que "esculta" a branch develop, qualquer comit nessa branch irá realizar o deploy no ambiente, todos os testes que foram implementados serão executados e a aplicação só será publicada caso todos os testes sejam executados com sucesso, no caso de testes com falha é disparado um e-mail me informando do problema.