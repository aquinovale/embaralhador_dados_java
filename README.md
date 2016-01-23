Programa feito em Java por Vinicius Vale 21/01/2016

Blog: www.viniciusvale.com <br/> 
Empresa: www.valeconsultoria.com <br />
Linkedin: linkedin.com/in/aquinovale

# Embaralhador de Dados para DUMP Postgresql
Este é um programa que tem como objetivo embaralhar alguns campos em um dump.

### ARQUIVO DUMP
O arquivo de dump deve ser um dump simples feito pelo POSTGRESQL de versão 8.4 a 9.5, 
dump's que contiverem comando INSERT em vez do COPY não funcionarão


### ARQUIVO OFUSCADOR
O arquivo ofuscador é basicamente um arquivo com tabela.campo <br/>
Ex.: clientes.cpf <br/>

Caso a tabela esteja definida com Maiuscula, então deve se usar aspas duplas <br/>
Ex.: "Pessoas".cpf <br/>
Ex.: "Pessoas"."CPF" <br/>


### ARQUIVO dump_shuffle
O arquivo dump_shuffle será criado no diretório de execução.<br/>
O procedimento de restore é o mesmo.



# Execução
java -jar embaralhador.jar DUMP ofuscadores












