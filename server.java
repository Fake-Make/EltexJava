private class BasicBroadcasting extends Thread {
	private String message;

	public BasicBroadcasting(String message) {
		this.message = message;
	}

	@Override
	public void start() {
		sendByUdp(BROADCAST, message);
	}
}

private class OrdersRecieving extends Thread {
	private ArrayList<Order> ordersPull;
	private HashMap<UUID, IP> clientsByOrderId;
	private IP somePort;

	public OrdersRecieving(ArrayList<Order> ordersPull, HashMap<UUID, IP> clientsByOrderId, IP somePort) {
		this.ordersPull = ordersPull;
		this.clientsByOrderId = clientsByOrderId;
		this.somePort = somePort;
	}

	@Override
	public void start() {
		listenOnPort(somePort) {
			Order newOrder = somePort.stream.readObject;
			synchronized(ordersPull) {
				ordersPull.add(newOrder);
				clientsByOrderId.addByKey(newOrder.getId(), somePort.client);
			}			
			close(connection);
		}
	}
}

private class OrdersProcessing extends Thread {
	private ArrayList<Order> ordersPull = new ArrayList<>();
	private HashMap<UUID, IP> clientsByOrderId = new HashMap<>();

	public OrdersRecieving(ArrayList<Order> ordersPull, HashMap<UUID, IP> clientsByOrderId, IP somePort) {
		this.ordersPull = ordersPull;
		this.clientsByOrderId = clientsByOrderId;
		this.somePort = somePort;
	}

	@Override
	public void start() {
		while (true) {
			synchronized(ordersPull) {
				int ordersPullLength = ordersPull.size();
				for (int i = 0; i < ordersPullLength; i++) {
					Order item = ordersPull.get(i);

					if (OrderStatus.PROCESSED == item.getStatus)
						ordersPull.delete(i);
					if (OrderStatus.AWAITING == item.getStatus) {
						item.setStatus(OrderStatus.PROCESSED);
						IP toSend = clientsByOrderId.getByKey(item.getId());
						sendByUdp(toSend, item);
					}
				}
				sleep(5000);
			}
		}
	}
}

class Server {
	private ArrayList<Order> ordersPull;
	private HashMap<UUID, IP> clientsByOrderId;

	public static void main(String[] args) {
		// Main collections
		private ArrayList<Order> ordersPull = new ArrayList<>();
		private HashMap<UUID, IP> clientsByOrderId = new HashMap<>();

		// Create threads
		Thread basicBroadcast = new BasicBroadcasting(myIp);
		Thread ordersReciever = new OrdersRecieving();
		Thread ordersProcessor = new OrdersProcessing();

		// Start threads
		basicBroadcast.start();
		ordersReciever.start();
		ordersProcessor.start();
	}
}