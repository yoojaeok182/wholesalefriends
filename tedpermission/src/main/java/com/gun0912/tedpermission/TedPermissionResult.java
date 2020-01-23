package com.gun0912.tedpermission;

import com.gun0912.tedpermission.util.ObjectUtils;
import java.util.ArrayList;

public class TedPermissionResult {

  private boolean granted;
  private boolean finalDenied;
  private ArrayList<String> deniedPermissions;

  public TedPermissionResult(ArrayList<String> deniedPermissions, boolean isFinalDenied) {
    this.granted = ObjectUtils.isEmpty(deniedPermissions);
    this.deniedPermissions = deniedPermissions;
    this.finalDenied = isFinalDenied;
  }

  public boolean isGranted() {
    return granted;
  }

  public boolean isFinalDenied(){
    return finalDenied;
  }

  public ArrayList<String> getDeniedPermissions() {
    return deniedPermissions;
  }
}
