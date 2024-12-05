public class UserRemove {
    AssignTrainer assignTrainer = new AssignTrainer();

    public void memberRemove(Gym gym,String email){
        for (Member member: gym.members){
            if(email.equals(member.getEmailAddress())) {
                gym.removeMembers(member);
                System.out.println("Member removed successfully");
                assignTrainer.memberUnassigned(gym,member.getTrainerChose(),member);
                break;
            }
        }
        WriteToFile.writeMembers(gym.members,false);
        WriteToFile.writeTrainer(gym.trainers,false);
    }
    public void trainerRemove(Gym gym,String ID){
        for (Trainer trainer: gym.trainers){
            if(ID.equals(trainer.getTrainerID())) {
                gym.removeTrainers(trainer);
                System.out.println("Trainer removed successfully");

                break;
            }
        }
        WriteToFile.writeTrainer(gym.trainers,false);
    }

    public void equipmentRemove(Gym gym,String ID){
        for (Equipment equipment: gym.equipments){
            if(ID.equals(equipment.getEquipmentID())) {
                gym.removeEquipment(equipment);
                System.out.println("Equipment removed successfully");
                break;
            }
        }
        WriteToFile.writeInventory(gym.equipments,false);
    }


}
