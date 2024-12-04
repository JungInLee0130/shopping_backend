package com.example.marketapi.directory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "DIRECTORIES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Directory {
    @Id
    private String _id;
    private String uuid;
    private String name;
    @Field(name = "is_directory")
    private boolean directory;
    @Field(name = "parent_uuid")
    private String parentUUID;
    @DBRef(lazy = true)
    private List<Directory> children;
    private String content;
    @Field(name = "workspace_uuid")
    private String workspaceUUID;
    @Field(name = "user_uuid")
    private String userUUID;
    @Field(name = "deleted")
    private boolean deleted;
    @Field(name = "editable")
    private boolean editable;

    public void editName(String name){
        this.name = name;
    }
    public void updateContent(String content){
        this.content = content;
    }
    public void updateChildren(List<Directory> children){
        this.children = children;
    }
    public void updateParentUUID(String parentUUID){
        this.parentUUID = parentUUID;
    }
    public void updateEditable(boolean editable){
        this.editable = editable;
    }

    @Override
    public boolean equals(Object obj) {
        Directory d = (Directory) obj;
        if(d.getUuid().equals(uuid)){
            return true;
        }
        return super.equals(obj);
    }
}
