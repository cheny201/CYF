//-------------------------------------------------------
// Common Functions

function _MarmotRPC_getResolver() {
	return this._resolver;
}

function _MarmotRPC_setResolver(resolver) {
	this._resolver = resolver;
}

//-------------------------------------------------------
// MarmotRPCCommand

function MarmotRPCCommand(id, viewModel) {	
	this.constructor.call(this, id, viewModel);
	this._resolver = null;
}

MarmotRPCCommand.prototype = new RPCCommand();
MarmotRPCCommand.prototype.constructor = RPCCommand;

DoradoFactory.registerComponentType("MarmotRPCCommand", function(id, viewModel) {
		return new MarmotRPCCommand(id, viewModel);
	}
);

MarmotRPCCommand.prototype.getResolver = _MarmotRPC_getResolver;
MarmotRPCCommand.prototype.setResolver = _MarmotRPC_setResolver;
MarmotRPCCommand.prototype._superFireBeforeExecute = MarmotRPCCommand.prototype._fireBeforeExecute;

MarmotRPCCommand.prototype._fireBeforeExecute = function() {
	this._superFireBeforeExecute();
	this.parameters().setValue("_resolver", this._resolver);
}

//-------------------------------------------------------
// MarmotUpdateCommand

function MarmotUpdateCommand(id, viewModel) {
	this.constructor.call(this, id, viewModel);
	this._resolver = null;
}

MarmotUpdateCommand.prototype = new UpdateCommand();
MarmotUpdateCommand.prototype.constructor = UpdateCommand;

DoradoFactory.registerComponentType("MarmotUpdateCommand", function(id, viewModel) {
		return new MarmotUpdateCommand(id, viewModel);
	}
);

MarmotUpdateCommand.prototype.getResolver = _MarmotRPC_getResolver;
MarmotUpdateCommand.prototype.setResolver = _MarmotRPC_setResolver;
MarmotUpdateCommand.prototype._superFireBeforeExecute = MarmotUpdateCommand.prototype._fireBeforeExecute;

MarmotUpdateCommand.prototype._fireBeforeExecute = function() {
	this._superFireBeforeExecute();
	this.parameters().setValue("_resolver", this._resolver);
}