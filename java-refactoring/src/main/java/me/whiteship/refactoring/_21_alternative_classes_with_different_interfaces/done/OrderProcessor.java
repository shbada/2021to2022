package me.whiteship.refactoring._21_alternative_classes_with_different_interfaces.done;

public class OrderProcessor {

    private EmailService emailService;
    // NotificationService로 감싼다.
    private NotificationService notificationService;

    public OrderProcessor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void notifyShipping(Shipping shipping) {
        Notification notification = Notification.newNotification(shipping.getOrder() + " is shipped")
                        .receiver(shipping.getEmail())
                        .sender("seohae@naver.com");

        sendNotification(notification);
    }

    private void sendNotification(Notification notification) {
        notificationService.sendNotification(notification);

        // NotificationService의 구현체로 옮긴다.
//        EmailMessage emailMessage = new EmailMessage();
//        emailMessage.setTitle(shipping.getOrder() + " is shipped");
//        emailMessage.setTo(shipping.getEmail());
//        emailMessage.setFrom("no-reply@whiteship.com");
//        emailService.sendEmail(emailMessage);
    }

}
