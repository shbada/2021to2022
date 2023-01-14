package me.whiteship.refactoring._21_alternative_classes_with_different_interfaces.done;

public class OrderAlerts {

    private AlertService alertService;
    private NotificationService notificationService;

    public OrderAlerts(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void alertShipped(Order order) {
        Notification notification = Notification.newNotification(order.toString() + " is shipped");
        sendNotification(notification);
    }

    private void sendNotification(Notification notification) {
        notificationService.sendNotification(notification);
//        AlertMessage alertMessage = new AlertMessage();
//        alertMessage.setMessage(order.toString() + " is shipped");
//        alertMessage.setFor(order.getEmail());
//        alertService.add(alertMessage);
    }
}
