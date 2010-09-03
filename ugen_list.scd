// these ugens from the standard distribution are still missing:
// (note this list was generated from sclang in summer 2009.
//  please let me know if ugens are missing because they had
//  been added to the standard library in the meantime)

[ LagControl,
DecodeB2, PanB2, PanB, BiPanB2,
GrainSin, GrainFM, GrainBuf, GrainIn, Warp1,
// ScopeOut, SharedOut, SharedIn,  // mac internal server only : we won't support these ugens for the time being
// DynKlank, DynKlang, HilbertFIR, Tap, IndexL, SelectX, SelectXFocus, TChoose, TWChoose, CompanderD, // fake ugens : we won't implement them right now
Dpoll,
MaxLocalBufs, ClearBuf, SetBuf, LocalBuf,  // these are urgent, but require a bit of extra work
PV_JensenAndersen, PV_HainsworthFoote, PV_ConformalMap, PackFFT, UnpackFFT, Unpack1FFT,
// AccelerometerX, AccelerometerY, AccelerometerZ // these seem to have disappeared from the standard distrib
QuadN, QuadL, QuadC, CuspN, CuspL, GbmanN, GbmanL, HenonN, HenonL, HenonC, LatoocarfianN, LatoocarfianC, LatoocarfianL, LinCongN, LinCongL, LinCongC, StandardN, StandardL, FBSineN, FBSineL, FBSineC, LorenzL,
Spring, Ball, TBall,
PartConv,
Pluck,
MoogFF

].size
