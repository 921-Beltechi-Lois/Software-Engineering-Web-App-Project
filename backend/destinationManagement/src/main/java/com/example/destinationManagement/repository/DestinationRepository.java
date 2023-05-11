//package com.example.destinationManagement.repository;
//
//import com.example.destinationManagement.domain.Destination;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class DestinationRepository {
//
//    private List<Destination> destinations = new ArrayList<>();
//
//    public Destination add(Destination newDestination) {
//        newDestination.setDestination_id(destinations.size() + 1);
//        destinations.add(newDestination);
//        return newDestination;
//    }
//
//    public void remove(int destination_id) {
//        destinations.removeIf(destination -> destination.getDestination_id() == destination_id);
//    }
//
//    public Destination update(Destination updatedDestination) {
//        int index = destinations.indexOf(findDestinationById(updatedDestination.getDestination_id()));
//        if (index != -1) {
//            destinations.set(index, updatedDestination);
//            return updatedDestination;
//        }
//        return null;
//    }
//
//    public List<Destination> findAllDestinations() {
//        return destinations;
//    }
//
//    public Destination findDestinationById(int destination_id) {
//        for (Destination destination : destinations) {
//            if (destination.getDestination_id() == destination_id) {
//                return destination;
//            }
//        }
//        return null;
//    }
//
//    public List<Destination> getPrivateDestinations() {
//        List<Destination> privateDestinations = new ArrayList<>();
//        for (Destination destination : destinations) {
//            if (destination.isPrivate()) {
//                privateDestinations.add(destination);
//            }
//        }
//        return privateDestinations;
//    }
//
//    public List<Destination> getPublicDestinations() {
//        List<Destination> publicDestinations = new ArrayList<>();
//        for (Destination destination : destinations) {
//            if (!destination.isPrivate()) {
//                publicDestinations.add(destination);
//            }
//        }
//        return publicDestinations;
//    }
//
//    public Destination getDestination(int destination_id) {
//        for (Destination destination : destinations) {
//            if (destination.getDestination_id() == destination_id) {
//                return destination;
//            }
//        }
//        return null;
//    }
//}
//
