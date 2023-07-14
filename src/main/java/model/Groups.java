package model;

public class Groups {
        private int groups_id;
        private String groups_name;

    public int getId() {
        return groups_id;
    }

    public String getName() {
        return groups_name;
    }

    public Groups(int id, String name) {
            this.groups_id = id;
            this.groups_name = name;
        }
    }
