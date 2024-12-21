package edu.miu.cs544.awais.EventManagementService.shared;

import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.staff.domain.Staff;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public class EventSpecification {
    public static Specification<Event> beforeDatePredicate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("date"), date);
        };
    }

    public static Specification<Event> afterDatePredicate(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> {
            if (date == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("date"), date);
        };
    }

    public static Specification<Event> nameLikePredicate(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }


    public static Specification<Event> staffInPredicate(List<Long> staffIds) {
        return (root, query, criteriaBuilder) -> {
            if (staffIds == null || staffIds.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<Event, Staff> staffJoin = root.join("staff");
            return staffJoin.get("emId").in(staffIds);
        };
    }

    public static Specification<Event> locationPredicate(Long locationId) {
        return (root, query, criteriaBuilder) -> {
            if (locationId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("location").get("emId"), locationId);
        };
    }

    public static Specification<Event> categoryPredicate(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("category").get("emId"), categoryId);
        };
    }

    public static Specification<Event> priceLessThan(Double price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("ticketPrice"), price);
        };
    }

    public static Specification<Event> priceGreaterThan(Double price) {
        return (root, query, criteriaBuilder) -> {
            if (price == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("ticketPrice"), price);
        };
    }


}
