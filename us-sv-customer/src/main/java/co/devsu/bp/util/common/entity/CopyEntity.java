package co.devsu.bp.util.common.entity;

public interface CopyEntity<ENTITY> {
    ENTITY copyFrom(ENTITY item);
}
