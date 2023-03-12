package DOUYU;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class MessageQueue<T> extends ArrayBlockingQueue<T> {
	/** 弹幕队列最大存储弹幕量 上限阈值 **/
	private static final Integer BARRAGE_MAX_SIZE = 1000;

	/** 弹幕队列正常弹幕量 **/
	private static final Integer BARRAGE_NORMAL_SIZE = 500;

	private Set<T> set = new CopyOnWriteArraySet();

	private static final long serialVersionUID = 3339707581882532469L;

	public MessageQueue(int capacity) {
		super(capacity);
	}

	@Override
	public boolean add(T e) {
		if (set.add(e)) {
			if (set.size() > BARRAGE_MAX_SIZE) {
				int total = 0;
				Set<T> newSet = new HashSet();
				Iterator<T> iterator = set.iterator();
				while (iterator.hasNext() && total++ < BARRAGE_NORMAL_SIZE) {
					newSet.add(iterator.next());
				}
				set = newSet;
			}
			return super.add(e);
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		set.remove(o);
		return super.remove(o);
	}

}
