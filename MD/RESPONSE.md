# API-kishijoten-voting


## STATUS

 - NO JENKINS 			= **NOT CONFIGURED**
 - DOCKER		 		= **OK** 
 - DB (SQL)		 		= **OK** 
 - DB (NOSQL)		 	= **NOT CONFIGURED** 
 - RABBITMQ		 		= **OK** 
 - UT 					= **OK**
 - IT 					= **OK**
 - AT 					= **NOT CONFIGURED**
 - Performance		 	= **NOT CONFIGURED**
 - Logging (Aspect) 	= **NOT CONFIGURED**
 - Logging (ELK Stack) 	= **NOT CONFIGURED**
 - API Documentation	= **NOT CONFIGURED**

## COMMENTS


### > API Documantation
	
	
 Current version Springboot + Swagger has an issue. 
 see expected result: https://swagger.io/tools/swagger-ui/
	

### > Testing
	
	
 - Complete all tasks was priority over testing
 
 
 - UT goal 

 achieve high coverage, but the expected for UT was supposed to be >80%
 
 - IT

one IT was create to illustrate one possibility using IT

 - AT
 
 NO AT was developed, but manual AT resource are in /MD/Insomnia_2020-05-19.json
 During development Inmonsina + SoapUI (REST) was used to ensure testing. 

 - Design (AbstractService)

 Command pattern could be used here to create a more testable solution, as spring uses in 

## RESPONSE

 - Cadastrar uma nova pauta :white_check_mark:
    
*[request example]:*

```

curl --request POST \
  --url http://localhost:8080/ruling \
  --header 'content-type: application/json' \
  --data '{
	"name":"ruling 01"
}'

```
  
  - Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default) :white_check_mark:
  
    - 1 minute default implementation com.github.jbrasileiro.voting.service.SessionServiceDefault.openSession(SessionOpenRequest):
      
    - UT 01 com.github.jbrasileiro.voting.service.SessionServiceDefaultTest.openSessionWithoutDuration()
    - UT 01 com.github.jbrasileiro.voting.service.SessionServiceDefaultTest.openSessionWithoutDuration()    

 *[request example]:*
 
```
		curl --request POST \
		  --url http://localhost:8080/session/open \
		  --header 'content-type: application/json' \
		  --data '{
			"idRulling":1
			,"duration":1
		}'
```


  - Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta) :white_check_mark:


 *[request example]:*

```
curl --request POST \
  --url http://localhost:8080/vote \
  --header 'content-type: application/json' \
  --data '{
	"session_id":4
	,"associate_id":4
	,"vote":"true"
}'
```
  
  
  - Contabilizar os votos e dar o resultado da votação na pauta :white_check_mark:

 *[request example]:*
 
```
curl --request GET \
  --url http://localhost:8080/vote/result/1

```


# Tarefa Bônus 1 - Integração com sistemas externos :white_check_mark:

  - Validação com CPF com.github.jbrasileiro.voting.service.CPFValidatorServiceDefault.validate(String)
  
# Tarefa Bônus 2 - Mensageria e filas :white_check_mark:

  - Utilização do RabbitMQ
  
  - Implementação: com.github.jbrasileiro.voting.service.SessionResultServiceDefault.updateSessionClosed()
  
# Tarefa Bônus 3 - Performance :x:
 
  - NOT DONE
 
# Tarefa Bônus 4 - Versionamento da API

 - 	Apesar da grande maioria implementer versionamento via URL, o que é mais simples, eu verionaria as API por HEADER, utilizado um campo especifico, evitaria ao meximo utilizar accepted e content-type.
 	
 	EX: Health (actuator)
 
 
# Observções 


Devido ao tempo optei por tentar concluir a implementação de toda a API.
Alguns pontos importante que não foram implementados:

 - Utilização de Aspecto para log
 - Configuração do Jenkins/sonar/PI/Coverage para garantir a qualidade do código também não foram implementadas.
 - i18n, não foi implementado
 - Utilizo bastante IT em níveis, considero importante a separação dele, porém, também não implementado:
   - src/test
   - src/test-integration, nível de componente (classes + classes) e E2E (testes consumindo a aplicação + possibilidade de configuração/mock de alguns pontos ) 
   - src/test-acceptance - Testes E2E consumindo a aplicação em nível de sistema
   - src/BDD - (cucumber)
   
 - Considero importante favorecer interfaces (OOPD), isso facilita os testes (Mock/Stub).
 
 - Documentação da API não foi implementada por há um conflito entre as novas versões springboot + swagger (o que normalmente é minha primeiro opção ao optar por um framework de documentação), optei por não investir muito tempo.
 
 - Refinamento de DB (migration, transation), tratamente de 4xx, exceções inesperadas também não foram implementadas. 
 
 - Utilização do RabbitMQ foi arbitraria.
 
 - Utiização de uma abordagem cloud também não foi utilizado (spring-cloud ie.)
 
 