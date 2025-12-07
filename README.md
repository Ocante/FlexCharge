#⚡ FlexCharge  
Aplicativo Android desenvolvido para demonstração de cálculos de abastecimento:  
• Combustível  
• Carga Elétrica  

Este projeto foi criado com foco educacional, aplicando conceitos de **Fragments**, **ViewBinding**, **organização de layouts**, e **boas práticas de arquitetura simples**.

---

## Objetivo do Projeto

O FlexCharge permite que o usuário selecione uma das opções no Spinner:

- **Combustível**
- **Elétrico**

Quando selecionado, o aplicativo carrega dinamicamente o fragment correspondente.  
Cada fragment possui campos de entrada, botões de cálculo e opções de limpar os dados.

Esse projeto demonstra:

- Uso de `FragmentManager`
- Substituição dinâmica de fragments
- Organização modular de código (pasta `fuel/` e `electric/`)
- Navegação simples através de Spinner
- Boas práticas de separação entre Activity e Fragments

---

## Tecnologias Utilizadas

- **Kotlin**
- **Android Studio**
- **AndroidX / Fragments**
- **ViewBinding**
- **Layouts XML Responsivos**

---


---

## Funcionamento

1. O usuário abre o aplicativo.
2. Escolhe no Spinner:
   - “Combustível” → abre `FuelFragment`
   - “Elétrico” → abre `ElectricFragment`
3. O fragment aberto exibe inputs e botões de cálculo.
4. O resultado é exibido diretamente na tela.

A **MainActivity** apenas controla a navegação.  
Cada fragment faz o cálculo correspondente.

---

## Imagens da Aplicação

![FlexCharge](./assets/test_App_FlexCharge.png)



## Demonstração

![App Flex](./assets/test_App_Flex.png)



## Como Executar o Projeto

Abra o projeto no Android Studio

Aguarde sincronizar o Gradle

Execute no emulador ou dispositivo real

Selecione a opção desejada no Spinner

Use os botões de calcular / limpar



## Autor: @Ocante António

Projeto desenvolvido como parte de atividade acadêmica — FlexCharge.



