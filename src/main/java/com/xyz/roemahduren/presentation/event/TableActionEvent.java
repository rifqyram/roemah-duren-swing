package com.xyz.roemahduren.presentation.event;

public interface TableActionEvent {

    void onEdit(int row);
    void onDelete(int row);

}
