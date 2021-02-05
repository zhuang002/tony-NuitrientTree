/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutienttree;

import java.util.Scanner;

/**
 *
 * @author zhuan
 */
public class NutientTree {

    /**
     * @param args the command line arguments
     */
    
    static Node root=new Node();
    static int x0;
    static Node[] nodes=new Node[5000];
    static int[][] cache=null;
    static int idCount=0;
    public static void main(String[] args) {
        // TODO code application logic here
        ReadInput();
        System.out.println(getMaxNuitrient(x0,root.id));
    }

    private static int getMaxNuitrient(int x, int id) {
        int result=0;
        Node parent=nodes[id];
        if (parent.left==null && parent.right==null) {
            return parent.weight+x;
        }
        if (cache[x][id]!=-1) 
            return cache[x][id];
        for (int x1=0;x1<=x0;x1++) {
            for (int y1=0;y1<=x1;y1++) {
                for (int y2=0;y2<=x0-x1;y2++) {
                    int tmp=Math.min(getMaxNuitrient(x1-y1,parent.left.id), (1+y1*y1))
                        +Math.min(getMaxNuitrient(x0-x1-y2,parent.right.id), (1+y2*y2));
                    if (tmp>result) {
                        result=tmp;
                    }
                }
            }
        }
        cache[x][id]=result;
        return result;
    }

    private static void ReadInput() {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        x0=sc.nextInt();
        
        root=parseTreeString(s);
        cache=new int[x0][idCount];
        for (int i=0;i<x0;i++) {
            for (int j=0;j<idCount;j++)
                cache[i][j]=-1;
        }
    }

    private static Node parseTreeString(String s) {
        int count=0;
        String leftString;
        String rightString;
        Node root=new Node();
        root.id=idCount;
        nodes[root.id]=root;
        idCount++;
        
        if (s.charAt(0)!='(') {
            root.weight=Integer.parseInt(s);
            return root;
        }
        
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if (c=='(') {
                count++;
            } else if (c==')') {
                count--;
            } else if (c==' ' && count==1) {
                leftString=s.substring(1,i);
                rightString=s.substring(i+1,s.length()-1);
                root.left=parseTreeString(leftString);
                root.right=parseTreeString(rightString);
            }
        }
        return root;
    }
}

class Node {
    int id;
    Node left;
    Node right;
    int weight=0;
}
        
/*stuct {
   int x,y,z;
}

typedef struct  node_structure{
    int id;
    struct Node *left=0;
    struct Node *right=0;
    int weight;
}* Node;




Node root=malloc(sizeof(Node));
root->id=1;
root->left=

root->id;
leftTree=root->left;
leftLeftTree=leftTree->left;*/
