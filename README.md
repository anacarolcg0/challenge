# 🚀 SOLUÇÃO PARA MOTTU

## 👥 Integrantes

- Ana Carolina de Castro Gonçalves - RM554669
- Luisa Danielle - RM555292
- Michelle Marques Potenza - RM557702

---

## 📄 Descrição do Projeto
- Esta aplicação tem como objetivo centralizar e organizar o gerenciamento de motocicletas nos pátios da empresa Mottu, por meio de Spring Security (MVC) + Thymeleaf.

- O sistema permite o cadastro, atualização, listagem e remoção de informações relacionadas a quatro entidades principais:
    - Clientes
    - Funcionários
    - Motos
    - Pátios

Além disso, o sistema foi expandido para incluir dois **fluxos adicionais de negócio**, que aprimoram a experiência do usuário e a eficiência operacional.

---

## ⚙️ Fluxos Implementados

### 🔧 Fluxo de Diagnóstico
Permite o registro e acompanhamento de diagnósticos técnicos das motos.

**Funcionalidades:**
- Cadastro de novos diagnósticos com modelo, ano e descrição do problema.
- Atualização do status do diagnóstico (PENDENTE, EM ANÁLISE, RESOLVIDO).
- Listagem detalhada dos diagnósticos realizados.
- Interface intuitiva com modal de visualização de detalhes e atualização de status.

**Objetivo:**  
Centralizar o processo de identificação e acompanhamento de falhas mecânicas, garantindo agilidade na manutenção das motos.

---

### 📅 Fluxo de Agendamento
Permite o agendamento de serviços, inspeções ou manutenções das motos.

**Funcionalidades:**
- Cadastro de novos agendamentos informando cliente, modelo da moto, data e descrição do serviço.
- Atualização de status do agendamento diretamente pela interface (PENDENTE, EM ANÁLISE, RESOLVIDO).
- Visualização clara e organizada de todos os agendamentos existentes.
- Modal de detalhes para acompanhamento e alteração de status.

**Objetivo:**  
Otimizar o controle de serviços agendados, evitando conflitos de horário e garantindo atendimento eficiente aos clientes.

---

## 🛠️ Como Rodar o Projeto Localmente

- Clone o repositório
- Navegue até a pasta do projeto
- Execute o comando: ./gradlew bootRun
- Acesse a aplicação em: http://localhost:8080

MAIS INFORMAÇÕES DA SEGURANÇA OAUTH ESTÃO NO ARQUIVO .TXT