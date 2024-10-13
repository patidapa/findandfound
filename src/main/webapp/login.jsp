<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

  
  
<div class="bg-color">

<div [hidden]="submitted">
  <form (ngSubmit)="onSubmit()" class="page" #userLogin=ngForm>
  <div class="container">
  <div class="panel panel-default">
     <div class="panel-heading">
       <h2 class="panel-title">User Registration</h2>
     </div>
     <div class="panel-body">
      <!-- email and phonenumber-->
      <div class="row">
          <div class="col-sm-6">
              <div class="form-group" >
                  <label for="email">Email:</label>
                  <input type="email"  required id="email"  [(ngModel)]="login.email" 
                  class="form-control"   name="email" #Email="ngModel" >
                  
                </div>
                <div class="vadiation" *ngIf="Email.invalid && Email.touched">This field is required</div>     
          
            </div>
      </div>
      <!--password fields-->
 
      <div class="row">
          <div class="col-sm-6">
            <div class="form-group" >
              <label for="password" >Password:</label>
              <input type="password" required #ConfirmControl="ngModel"
               id="password" class="form-control" [(ngModel)]="login.password"
               name="password">
            </div>
            <div class="vadiation" *ngIf="ConfirmControl.invalid && ConfirmControl.touched">This field is required</div>
  
         </div>
     </div>
      </div>
      <div class="panel-footer">
        <button type="submit"  class="btn btn-success">Submit</button>
       
       </div> 
    </div>
    
  </div>
  
</form>
</div>
</div>