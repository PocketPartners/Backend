package fairfinance.pocketpartners.backend.groups.interfaces.rest.resources;

import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;

public record GroupMemberResource (Long groupId, Long userId, String name, String groupPhoto, String fullName, String email, String phoneNumber, java.util.Date joinedAt){
    public static Object fromCommandToResource(GroupMember groupMember) {
        return new GroupMemberResource(groupMember.getGroup().getId(), groupMember.getUserInformation().getId(), groupMember.getGroup().getName(),groupMember.getGroup().getGroupPhoto(), groupMember.getUserInformation().getFullName(), groupMember.getUserInformation().getEmailAddress(), groupMember.getUserInformation().getPhoneNumber(), groupMember.getJoinedAt());
    }
}
