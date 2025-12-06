# Desenvolvimento de um Sistema CRUD com Testes Unitários

Este repositório contém o código-fonte e a documentação de um estudo de caso sobre o impacto da implementação de testes unitários no desenvolvimento de sistemas CRUD (Create, Read, Update, Delete).

O projeto compara duas versões de um mesmo sistema de gerenciamento de usuários para analisar métricas de qualidade de software, manutenibilidade e detecção de erros.

## 👥 Autores

* **Guilherme Brito Duques**
* **João Pedro Calçada Ribeiro**
* **Lucas Oliveira de Lima**
* **Gustavo Brum Fernandes Pimentel**

---

## 🎯 Objetivo do Projeto

O objetivo principal é investigar como a introdução de testes unitários impacta a clareza do código, a facilidade de manutenção e a detecção de defeitos em sistemas de informação.

Para isso, o projeto foi dividido em duas abordagens:
1.  **Versão A:** Implementação funcional sem testes unitários (validação manual).
2.  **Versão B:** Implementação com uma suíte de testes automatizados utilizando JUnit.

## 🛠️ Tecnologias Utilizadas

O sistema foi desenvolvido mantendo intencionalmente o domínio simples e a persistência em memória para focar na análise da testabilidade e estrutura do código.

| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| **Java** | 21 | Linguagem de programação |
| **Maven** | 4.0.0 | Gerenciamento de dependências e build |
| **JUnit** | 5.13.4 | Framework de testes unitários |
| **Git** | - | Controle de versão |

## 📂 Estrutura do Repositório

O experimento está organizado em *branches* para facilitar a comparação:

* `main`: Contém a **Versão A** (código estável, porém sem testes unitários).
* `com-testes`: Contém a **Versão B** (código com a suíte de testes implementada e refatorações de design).

## 🚀 Como Executar

### Pré-requisitos
* Java JDK 21 instalado.
* Maven instalado.

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
    cd seu-repositorio
    ```

2.  **Para rodar a versão SEM testes (Simulação Manual):**
    ```bash
    git checkout main
    mvn clean install
    # A execução depende de testes manuais via Main class ou CLI
    ```

3.  **Para rodar a versão COM testes (Automatizado):**
    ```bash
    git checkout com-testes
    mvn clean test
    ```

    *Resultado esperado:* O console exibirá o relatório do Surefire com o status dos testes (ex: `Tests run: 18, Failures: 0`)[cite: 113, 120].

## 📊 Metodologia do Experimento

Durante o estudo, foram introduzidos defeitos intencionais (Injeção de Falhas) em ambas as versões para medir a eficiência na detecção. Exemplos de falhas injetadas:
* Remoção de validação de e-mail obrigatório.
* Troca de operadores lógicos.

O sistema cobre as operações fundamentais:
* **Create:** Cadastro de usuários.
* **Read:** Listagem e consulta.
* **Update:** Atualização de dados.
* **Delete:** Remoção de registros.

## 📈 Resultados e Conclusões

O estudo demonstrou que a adoção de testes unitários trouxe benefícios tangíveis:

1.  **Detecção Imediata:** Na versão com testes, falhas injetadas quebraram o build imediatamente (`BUILD FAILURE`), apontando a linha exata do erro.
2.  **Refatoração Segura:** A suíte de testes funcionou como um "mapa", permitindo alterar a estrutura do código com confiança e sem medo de regressões.
3.  **Documentação Viva:** Os testes serviram como documentação executável, explicando o comportamento esperado do sistema melhor que comentários estáticos.
4.  **Modularidade:** A necessidade de testar incentivou a criação de métodos menores e mais coesos (Princípios SOLID).

---

### Referência do Artigo
Este código é parte integrante do artigo: *"Desenvolvimento de um Sistema CRUD para Gerenciamento de Usuários com Testes Unitários"*.

Link para o artigo completo: [ResearchGate](https://www.researchgate.net/publication/398358337_Desenvolvimento_de_um_Sistema_CRUD_para_Gerenciamento_de_Usuarios_com_Testes_Unitarios).