Manual de instruções

**Funcionalidades Projeto 1:** 


1-	Criar figuras:
Clique com o botão esquerdo do mouse em qualquer posição do frame, depois, pressione uma das dadas teclas a seguir para criar a figura desejada na posição onde o mouse clicou. Caso o mouse não tenha sido clicado, a figura será criada no canto superior esquerdo do frame
r- Criar retângulo
t- Criar triângulo
e- Criar elipse
l- Criar linha

2-	Foco:
Quando uma figura for criada, o foco automaticamente irá até ela, porém, ele também pode ser manualmente decidido clicando com o botão esquerdo do mouse em uma figura. Assim que o foco for atribuído à uma figura, ele não será removido até outra figura ser clicada ou criada, ou até a cor de borda da figura em foco ser mudada. Além disso, a atribuição do foco irá colocar a figura selecionada na frente de todas as outras. O foco neste programa é representado por uma borda vermelha por volta de uma figura, e todas as ações a seguir apenas serão possíveis se houver uma figura em foco

3-	Mover o foco:
Enquanto o foco estiver em uma figura, pode-se pressionar a tecla TAB para passar o foco para outra figura. (O TAB muda o foco de uma figura para outra da lista de figuras. Quando várias figuras estiverem sobrepostas, o foco irá SEMPRE para a figura que estiver “por última” no frame (atrás de todas as outras))

4-	Mover a figura:
Há duas formas de mover a(s) figura(s) neste programa. Pressionando as setas do teclado (que irá mover a figura focada em suas respectivas direções), ou pressionando e segurando o botão esquerdo do mouse enquanto ele está dentro da figura em foco, e mover o mouse (a figura acompanhará a posição do mouse).

5-	Mudar as cores de fundo e borda da figura:
Pressionando as teclas 1,2 ou 3 enquanto uma figura está focada, irá respectivamente mudar a cor de fundo dela para ciano, rosa e amarelo (não irá mudar a cor da linha). Seguindo esta mesma lógica, as teclas 4,5 e 6 irão respectivamente mudar a cor da borda para azul, magenta ou laranja (irá mudar a cor da linha). Lembrando que, como dito anteriormente, mudar a cor da borda irá fazer a figura perder o foco

6-	Mudar o tamanho das figuras:
O tamanho das figuras pode ser mudado de duas formas, usando os botões + e – do teclado (que respectivamente dobram o tamanho de uma figura e reduzem seu tamanho pela metade), ou usando a roda do mouse, que irá aumentar o tamanho da figura quando a roda estiver sendo rodada para cima, ou diminuindo seu tamanho quando ela estiver sendo rodada para baixo.

7-	Deletar figuras:
Quaisquer figuras criadas ou modificadas no frame podem ser deletadas através da tecla delete, desde que o foco esteja na figura que o usuário deseja ser deletada.


**Funcionalidades Projeto 2:**

1-	Caixa de ferramentas/toolbar:
No lado direito do frame, foi adicionado uma lista de botões para facilitar a interação do usuário com a aplicação.
Modo de uso: Ao clicar em um botão, ele fica em foco(pressionado). Enquanto o botão estiver em foco, o clique do mouse no canvas estará associado a função do botão.

Os primeiros 4 botões (botões 1-4) são, em ordem, os botões de criação das figuras retângulo, elipse, triângulo e linha. Quando um desses botões estiver pressionados, um click em uma parte vazia do frame criará a figura selecionada. Mantenha em mente que: a) enquanto um destes 4 botões estiver pressionados, quando uma figura existente for clicada, ela receberá o foco e nenhuma figura será criada; e b) o botão pressionado não perderá o foco.

Os próximos 6 botões têm como função mudar a cor das figuras:

Os botões 5-7 mudarão a cor de fundo da figura.

Os botões 8-10 mudarão a cor de borda da figura.

Para usar esta função, clique em um desses 6 botões para colocá-los em foco. Após selecionar o botão, clique em quaisquer figuras existentes no canvas. A figura selecionada terá sua cor transformada de acordo com o botão selecionado. Mantenha em mente que após clicar em uma figura, com qualquer destes botões em foco, o foco será mantido no botão. Desse modo, o usuário pode, por exemplo, trocar a cor de fundo de várias figuras.

Finalmente, o último botão (botão 11), representado por um círculo de borda vermelha, tem como função tirar o foco de quaisquer botões do toolbar que esteja pressionado. Para usá-lo, apenas clique nele enquanto outro botão estiver em foco.

2-	Salvar o frame:
Quando o usuário fechar a aplicação, ela salvará quaisquer figuras adicionadas ou modificadas no frame em um arquivo nomeado <proj.bin>. Ao iniciar a aplicação novamente, a aplicação busca esse arquivo. Caso ele exista, as figuras existentes são carregadas e o Frame será apresentado com a mesma aparência de quando foi fechado.

3-	 Salvar em SVG:
Além de salvar um arquivo (.bin), as figuras do frame também serão salvas no formato SVG, no arquivo <Figuras.svg>. Esse arquivo poderá ser utilizado pelo usuário em uma ferramenta de edição de arquivos nesse formato.
Neste projeto, os testes para validar o arquivo (.svg) gerado, para verificar se as figuras salvas estavam com formatação correta, foram realizados utilizando o editor SVG online disponível em https://codepen.io/willianjusten/pen/dPWdbQ . Na pasta “Exemplos”, há uma imagem que demonstra a comparação das imagens geradas a partir do arquivo (.svg) salvo pela aplicação e visualizado no editor, com as imagens do Frame usando a aplicação deste projeto.
