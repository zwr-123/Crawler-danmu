package DOUYU;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Connect {
	private String url;
	private barrageHandle bh;
	private MessageQueue<DouYuBarrageMessage> Queue = new MessageQueue<>(2000);

	public Connect() {
		super();
	}

	public Connect(String url, barrageHandle bh) {
		super();
		this.url = url;
		this.bh = bh;
	}

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					DouYuBarrageMessage barrageMessage = Queue.poll();
					if (barrageMessage != null) {
						bh.DouYuHandle(barrageMessage);
					}
				}
			}
		}).start();
		
		
		
		 new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                	System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver.exe");
	                    ChromeOptions options = new ChromeOptions();
	                    //禁用一些选项，优化性能
	                    options.addArguments("headless");
	                    options.addArguments("disable-gpu");
	                    options.addArguments("disable-extensions");
	                    options.addArguments("blink-settings=imagesEnabled=false");
	                    ChromeDriver driver = new ChromeDriver(options);
	                    driver.get(url);
	                    Thread.sleep(10000);
	                    while (true) {
	                        try {
	                            WebElement element = driver.findElement(By.className("Barrage-list"));
	                            String value = element.getAttribute("innerHTML");
	                            addToQueue(value);
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                    }
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }).start();

	}

	protected void addToQueue(String value) {
		  Elements items = Jsoup.parse(value).select("li");
		  if(!items.isEmpty()) {
			  for (Element item : items) {
		        	Elements content = item.select("span[class='Barrage-content']");
		        	if(content.isEmpty()) {
		        		continue;
		        	}
		        	DouYuBarrageMessage barrageMessage = new DouYuBarrageMessage();
		        	barrageMessage.setMessage(content.text());
		        	barrageMessage.setIdOfLi(item.attr("id"));
		            Elements itemN = item.select("span[class*='Barrage-nickName']");
		            if (itemN != null) {
		                barrageMessage.setNickname(itemN.text());
		            }
		            Queue.add(barrageMessage);
		        }
		  }
		 
	       
		
	}
	
	

}