public class NumberSnakeTest1 {
    public static void main(String[] args) {

        NumberSnake ns = new NumberSnake();
        ns.addFirst('H', '1');
        System.out.println(ns.isValidSnake()); // false
        ns.addLast('1', 'T');
        System.out.println(ns.toString());

        System.out.println(ns.isValidSnake()); // true
        System.out.println();

        // The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
        char[] chs = { '1', '2', '2', '3', '3', '4', '4', '1' };
        ns.mergeSnakes(chs);
        System.out.println(ns.toString()); // (H,1)-(1,2)-(2,3)-(3,4)-(4,1)-(1,T)
        System.out.println();

        char[] chs1 = { '3', '6', '6', '7', '7', '7', '7', '3' };
        //ns.mergeSnakes(chs1);
        System.out.println(ns.mergeSnakes(chs1));
        System.out.println(ns.toString());
        // (H,1)-(1,2)-(2,3)-(3,6)-(6,7)-(7,7)-(7,3)-(3,4)-(4,1)-(1,T)
        System.out.println();

        ns.removeChunk();
        System.out.println(ns.toString()); // (H,1)-(1,T)
        System.out.println();

        ns.addLast('2', 'T');
        System.out.println(ns.toString()); // (H,1)-(1,T)-(2,T)
        System.out.println();

        // char[] chs2 = {'2','3','3','4','4','2','2'};
        // System.out.println(ns.mergeSnakes(chs2)); //false
        // System.out.println(ns.toString()); //(H,1)-(1,2)-(2,T)
        // ns.addFirst('2', '1');
        // ns.addFirst('H', '2');
        // System.out.println(ns.toString());
        // ns.addLast('0', '0'); //addFirst랑 addLast할 때 H랑 T를 다른 char로 대체?
        // System.out.println(ns.toString());
        // ns.addFirst('3', '3');
        // System.out.println(ns.toString());
        // ns.addFirst('H', '4');

        // NumberSnake ns = new NumberSnake();
        // ns.addFirst('H', '3');
        // ns.addLast('3', '4');
        // ns.addLast('4', '5');
        // ns.addLast('5', '7');
        // ns.addLast('7', '4');
        // ns.addLast('4', '5');
        // ns.addLast('5', '2');
        // ns.addLast('2', 'T');
        // System.out.println(ns.toString());

        // ns.removeChunk();
        // System.out.println(ns.toString());
        // System.out.println(ns.isValidSnake());// true
        // ns.removeFirst();
        // System.out.println(ns.isValidSnake());// false

        // NumberSnake ns = new NumberSnake();
        // ns.addFirst('H', '3');
        // ns.addLast('3', '4');
        // ns.addLast('4', '5');
        // ns.addLast('5', '6');
        // ns.addLast('6', '7');
        // ns.addLast('7', '5');
        // ns.addLast('5', '9');
        // ns.addLast('9', '1');
        // ns.addLast('1', '9');
        // ns.addLast('9', 'T');
        // System.out.println(ns.toString());

        // ns.removeChunk();
        // System.out.println(ns.toString());
        // //System.out.println(ns.isValidSnake() );//true
        // //ns.removeFirst();
        // System.out.println(ns.isValidSnake() );//false

        /*
         * System.out.println(ns.isValidSnake() ); //true
         * System.out.println();
         * 
         * // The following represents a body sequence (1,2)-(2,3)-(3,4)-(4,1)
         * char[] chs = {'1', '2', '2', '3', '3', '4', '4', '1'};
         * ns.mergeSnakes(chs);
         * System.out.println(ns.toString()); //(H,1)-(1,2)-(2,3)-(3,4)-(4,1)-(1,T)
         * System.out.println();
         * 
         * char[] chs1 = {'3', '6', '6', '7', '7', '7', '7','3'};
         * ns.mergeSnakes(chs1);
         * System.out.println(ns.toString());
         * //(H,1)-(1,2)-(2,3)-(3,6)-(6,7)-(7,7)-(7,3)-(3,4)-(4,1)-(1,T)
         * System.out.println();
         * 
         * ns.removeChunk();
         * System.out.println(ns.toString()); //(H,1)-(1,T)
         * System.out.println();
         * 
         * ns.addLast('2', 'T');
         * System.out.println(ns.toString()); //(H,1)-(1,2)-(2,T)
         * System.out.println();
         * 
         * char[] chs2 = {'2','3','3','4','4','2','2'};
         * System.out.println(ns.mergeSnakes(chs2)); //false
         * System.out.println(ns.toString()); //(H,1)-(1,2)-(2,T)
         * ns.addFirst('2', '1');
         * ns.addFirst('H', '2');
         * System.out.println(ns.toString());
         * ns.addLast('0', '0'); //addFirst랑 addLast할 때 H랑 T를 다른 char로 대체?
         * System.out.println(ns.toString());
         * ns.addFirst('3', '3');
         * System.out.println(ns.toString());
         * ns.addFirst('H', '4');
         */

    }

}
