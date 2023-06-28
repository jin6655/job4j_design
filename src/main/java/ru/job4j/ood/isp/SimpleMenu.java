package ru.job4j.ood.isp;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        MenuItem menuItemNew = new SimpleMenuItem(childName, actionDelegate);
        Optional<ItemInfo> find = findItem(parentName);
        if (find.isPresent()) {
            ItemInfo itemParent = find.get();
            DFSIterator dfsIterator = new DFSIterator();
            while (dfsIterator.hasNext()) {
                ItemInfo item = dfsIterator.next();
                if (itemParent.menuItem.getName().equals(item.menuItem.getName())) {
                    item.menuItem.getChildren().add(menuItemNew);
                    break;
                }
            }
        } else {
            rootElements.add(menuItemNew);
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        ItemInfo itemInfo = findItem(itemName).get();
        String name = itemInfo.menuItem.getName();
        List<String> children = itemInfo.menuItem.getChildren().stream().map(s -> s.getName()).collect(Collectors.toList());
        ActionDelegate actionDelegate = itemInfo.menuItem.getActionDelegate();
        String number  = itemInfo.number;
        return Optional.ofNullable(new Menu.MenuItemInfo(name, children, actionDelegate, number));
    }

    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfs = new DFSIterator();
        return new Iterator<MenuItemInfo>() {

            @Override
            public boolean hasNext() {
                return dfs.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                ItemInfo item = dfs.next();
                return new MenuItemInfo(item.menuItem, item.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        ItemInfo rsl = null;
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo item = dfsIterator.next();
            if (item.menuItem.getName().equals(name)) {
                rsl = item;
                break;
            }
        }
        return Optional.ofNullable(rsl);
    }

    private static class SimpleMenuItem implements MenuItem {
        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious(); ) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {
        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
