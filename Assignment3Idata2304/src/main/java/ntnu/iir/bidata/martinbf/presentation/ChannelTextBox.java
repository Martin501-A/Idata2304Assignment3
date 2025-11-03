package ntnu.iir.bidata.martinbf.presentation;

import javafx.scene.control.TextField;
import ntnu.iir.bidata.martinbf.entity.Remote;
import ntnu.iir.bidata.martinbf.entity.RemoteSubscriber;

/**
 * Is responsible for the textbox showing the channel for the remote.
 */
public class ChannelTextBox extends TextField implements RemoteSubscriber {
  private Remote remote;

  public ChannelTextBox(Remote remote) {
    super();
    this.remote = remote;
  }


  @Override
  public void update() {
    super.setText(getText());
  }
}
