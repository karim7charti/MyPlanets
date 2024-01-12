package com.example.myplanets;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "planet")
public class Planet {

    public void set_id(int _id) {
        this._id = _id;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int _id;

    @ColumnInfo(name = "name")
    private String name;

    public int getId() {
        return _id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
