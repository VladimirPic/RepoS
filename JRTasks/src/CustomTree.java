import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Serializable, Cloneable {

    Entry<String> root;

    List<Entry<String>> list = new ArrayList<>();


    public CustomTree() {
        this.root = new Entry<>("0");
        list.add(root);
    }


    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        if(list.get(2) == null) {
            return ((list.size()) / 2) + 1;
        }
        return list.size() - 1;
    }

    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    public boolean add(String name) {

        Entry<String> currentEntry = new Entry<>(name);

        for (Entry<String> entry : list) {

            if (entry.isAvailableToAddChildren()) {

                currentEntry.parent = entry;

                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = currentEntry;
                    entry.availableToAddLeftChildren = false;
                } else {
                    entry.rightChild = currentEntry;
                    entry.availableToAddRightChildren = false;
                }

                list.add(currentEntry);

                return true;
            }
        }
        return false;
    }

    String getParent(String s) {
        for (Entry<String> item : list) {
            if (item.elementName.equals(s))
                return item.parent.elementName;
        }
        return null;
    }

     public boolean remove(Object o) {
        if(!(o instanceof String)) {
            throw new UnsupportedOperationException();
        } else {
            for (Entry<String> el : list) {
                if (el.elementName.equals(o)) {
                    Entry<String> par = el.parent;
                    Entry<String> lchild = el.leftChild;
                    Entry<String> rchild = el.rightChild;

                    if(par.leftChild != null) {
                        if (par.leftChild.elementName.equals(o)) {
                            list.remove(el);
                            par.leftChild = null;

                            while (lchild != null) {
                                list.remove(lchild);
                                lchild = lchild.leftChild;
                            }
                            while (rchild != null) {
                                list.remove(rchild);
                                rchild = rchild.rightChild;
                            }
                        }

                        par.availableToAddLeftChildren = false;
                    }

                    if(par.rightChild != null) {
                        if (par.rightChild.elementName.equals(o)) {
                            list.remove(el);
                            par.rightChild = null;

                            while (lchild != null) {
                                list.remove(lchild);
                                lchild = lchild.leftChild;
                            }
                            while (rchild != null) {
                                list.remove(rchild);
                                rchild = rchild.rightChild;
                            }

                            par.availableToAddRightChildren = false;
                        }
                    }

                    if(!par.availableToAddLeftChildren && !par.availableToAddRightChildren && par.leftChild == null && par.rightChild == null) {
                        par.availableToAddLeftChildren = true;
                        par.availableToAddRightChildren = true;
                    }

                    return true;
                }
            }
        }
        return false;
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }


    }
}

