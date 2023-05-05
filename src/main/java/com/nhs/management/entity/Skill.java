package com.nhs.management.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "skills", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Skill {

    @Id
    @GeneratedValue(generator = "SKILL_GEN", strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Level level;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

@Converter(autoApply = true)
class LevelConverter implements AttributeConverter<Level, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Level level) {
        if (level == null) {
            return null;
        }
        return level.getLevel();
    }

    @Override
    public Level convertToEntityAttribute(Integer level) {
        if (level == 0) {
            return null;
        }

        return Stream.of(Level.values())
                .filter(c -> c.getLevel().equals(level))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}