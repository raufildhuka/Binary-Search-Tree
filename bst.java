public class bst
{
     Node root;

     private class Node
     {
          String keyword;
          Record record;
          int size;
          Node l;
          Node r;

          private Node(String k)
          {
              // TODO Instantialize a new Node with keyword k.
              keyword = k;
          }

          private void update(Record r)
          {
              // TODO Adds the Record r to the linked list of records. You do not
              // have to check if the record is already in the list.
              // HINT: Add the Record r to the front of your linked list.
              if (record == null)
              {
                   record = r;
              }
              else
              {
                   r.next = record;
                   record = r;
              }

          }

     }

     public bst()
     {
          this.root = null;
     }

     public void insert(String keyword, FileData fd)
     {
          Record recordToAdd = new Record(fd.id, fd.author, fd.title, null);
          // TODO Write a recursive insertion that adds recordToAdd to the list of
          // records for the node associated
          // with keyword. If there is no node, this code should add the node.
          // remove leading and trailing whitespace
          
          keyword = keyword.trim();
          insert(keyword, recordToAdd, root);//recursive call

     }

     //recursive insertion that adds recordToAdd to the list of

     // records for the node associated

     private void insert(String keyword, Record recordToAdd, Node root)
     {
          // if the root of the tree is null
          if (root == null) //if root is empty, create new node
          {
              Node node = new Node(keyword);
              node.update(recordToAdd);
              this.root = node;
          }

          else if (keyword.compareTo(root.keyword) < 0) //if keyword compared to root's keyword <0
          {
              if (root.l != null)//if left side of root is not empty, add keyword to left
              {
                   insert(keyword, recordToAdd, root.l);//recursive call
              }
              else
              {
                   Node node = new Node(keyword);//create new node
                   node.update(recordToAdd);//update record
                   root.l = node;//left of root is new node
              }

          }

          else if (keyword.compareTo(root.keyword) > 0)//if keyword compared to root's keyword >0
          {
              //check if the root of the tree is not null
              if (root.r != null)//if right side of root is not empty, add keyword to right
              {
                   insert(keyword, recordToAdd, root.r);//recursive call
              }
              else
              {

                   Node node = new Node(keyword);//create new node
                   node.update(recordToAdd);//update record
                   root.r = node;//right of root is new node
              }

          }
          else//if keyword node found
          {
              root.update(recordToAdd);//update record to add
          }

     }

     public boolean contains(String keyword)
     {
          // TODO Write a recursive function which returns true if a particular
          // keywordexists in the bst
          // call the recursive function contains()
          return contains(keyword, root);// calls contains

     }

     private boolean contains(String keyword, Node root)
     {

          if (root == null)
          {
              return false;
          }


          if (keyword.compareTo(root.keyword) < 0)//if keyword compared to root's keyword <0
          {
              return contains(keyword, root.l);// move to left side of the tree (recursive call)
          }

          else if (keyword.compareTo(root.keyword) > 0)//if keyword compared to root's keyword >0
          {
              return contains(keyword, root.r);// move to rigth side of the tree (recursive call)
          }

          else//keyword is in tree
          {
              return true;
          }

     }

     public Record get_records(String keyword)
     {
          // TODO Returns the first record for a particular keyword. This record
          // will link to other records
          // If the keyword is not in the bst, it should return null.
          Node current = root;

          while (current != null)//loop until node is null
          {

              if (keyword.compareTo(current.keyword) < 0)//if keyword compared to root's keyword <0
              {
                   current = current.l;//current node if left node
              }

              else if (keyword.compareTo(current.keyword) > 0)//if keyword compared to root's keyword >0
              {
                   current = current.r;//current node if right node
              }

              else // keyword found
              {
                   return current.record; // return current's record
              }

          }
          return null;
     }

     public void delete(String keyword)
     {
          // TODO Write a recursive function which removes the Node with keyword
          // from the binary search tree.
          // You may not use lazy deletion and if the keyword is not in the bst,
          // the function should do nothing.
          delete(keyword, root);//calls delete function
     }

     private Node delete(String keyword, Node current)
     {
          if (current == null) //if node is empty
          {
              return null;
          }

          else if (keyword.compareTo(current.keyword) < 0)//if keyword compared to root's keyword <0
          {
              current.l = delete(keyword, current.l);//recursive call
          }

          else if (keyword.compareTo(current.keyword) > 0)//if keyword compared to root's keyword ?0
          {
              current.r = delete(keyword, current.r);//recursive call
          }
          else
          {
              if (current.r == null)//if right of node is empty
              {
                   current = current.l;//left node becomes current
              }
              else
              {
                   Node replacement = smallestNode(current.r);//finds smallest node between right and left nodes
                   // move replacement node's values into current
                   current.keyword = replacement.keyword;
                   current.record = replacement.record;
                   current.size = replacement.size;

                   current.r = delete(replacement.keyword, current.r);//delete smallest node
              }
          }
          return current;
     }

     private Node smallestNode(Node root) 
     {
          if (root == null)//if root is empty
          {
              return null;
          }

          if (root.l == null) //if left node is empty
          {
              return root;
          }

          return smallestNode(root.l);//recursive call
     }

     public void print()
     {
          print(root);
     }

     private void print(Node t)
     {
          if (t != null)
          {
              print(t.l);
              System.out.println(t.keyword);
              Record r = t.record;
              while (r != null)
              {
                   System.out.printf("\t%s\n", r.title);
                   r = r.next;
              }
              print(t.r);
          }

     }

}