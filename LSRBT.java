public class LSRBT {

  private static node root = null;

  node rotateRight(node myNode) {
    System.out.printf("Вращаем узел вправо\n");
    node child = myNode.left;
    node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }
  //Повернули узел по часовой стрелке


  // Функция для поворота узла против часовой стрелки.
  node rotateLeft(node myNode) {
    System.out.printf("Поворот узла влево\n");
    node child = myNode.right;
    node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  } 
  //Повернули узел против часовой стрелки


  

  // Вводим логическую функцию,кооторая проверяла бы, является ли  цвет узла красным
  boolean isRed(node myNode) {
    if (myNode == null) {
      return false;
    }
    return (myNode.color == true);
  }

  //Изменяем цвета узлов.
  void swapColors(node node1, node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  
  node insert(node myNode, int data) {    // вставка в левостороннее красно-черное дерево.

    if (myNode == null) { // Обычный код вставки для любого двоичного файла
      return new node(data); 
    }

    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }


    // случай 1
    //  Левый ребёнок и левый внук выделены красным цветом
    if (isRed(myNode.left) && isRed(myNode.left.left)) {

      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right); // Поворот узла вправо
    }

// случай 2.
    // Правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
    if (isRed(myNode.right) && !isRed(myNode.left)) {
// Поворот узла влево
      myNode = rotateLeft(myNode);

// Поменять местами цвета дочернего узла,он всегда должен быть красным
      swapColors(myNode, myNode.left);
    }


// случай 3
    // Левый и правый дочерние элементы окрашены в красный цвет.
    if (isRed(myNode.left) && isRed(myNode.right)) {
// Инвертировать цвет узла 
      myNode.color = !myNode.color;

// Изменяем цвет на черный.
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

 
  void inorder(node node) {
    if (node != null)
    {
      inorder(node.left);
      char c = '●';
      if (node.color == false)
        c = '◯';
      System.out.print(node.data + ""+c+" ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {

    LSRBT node = new LSRBT();
    Scanner scan = new Scanner(System.in);

    char ch;
    do {
      System.out.println("Целое число");

      int num = scan.nextInt();
      root = node.insert(root, num);

      node.inorder(root);
      System.out.println("\nПродолжить? ( y/n)");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');
  }
}